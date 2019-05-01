import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { defer, scheduled, Observable } from 'rxjs';
import { asap } from 'rxjs/internal/scheduler/asap';
import { catchError, map, switchMap, withLatestFrom, skipWhile, mergeMap } from 'rxjs/operators';

import { AppState } from '../';
import { StompSubscription } from '../../model/stomp';

import { AlertService } from '../../service/alert.service';
import { StompService } from '../../service/stomp.service';

import * as fromStomp from './stomp.actions';

@Injectable()
export class StompEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private stomp: StompService,
        private alert: AlertService
    ) {

    }

    @Effect() connect = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.CONNECT),
        switchMap(() =>
            this.stomp.connect().pipe(
                map(() => new fromStomp.ConnectSuccessAction()),
                catchError((response) => scheduled([new fromStomp.ConnectFailureAction({ response })], asap))
            )
        )
    );

    @Effect() connectFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.CONNECT_FAILURE),
        map(() => this.alert.connectFailureAlert())
    );

    @Effect() disconnect = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.DISCONNECT),
        map((action: fromStomp.DisconnectAction) => action.payload),
        withLatestFrom(this.store),
        switchMap(([payload, state]) => {
            const reconnect = payload.reconnect;
            state.stomp.subscriptions.forEach((subscription: StompSubscription, channel: string) => {
                subscription.unsubscribe();
            });
            return this.stomp.disconnect().pipe(
                map(() => new fromStomp.DisconnectSuccessAction({ reconnect })),
                catchError((response) => scheduled([new fromStomp.DisconnectFailureAction({ response })], asap))
            );
        })
    );

    @Effect() disconnectSuccess = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.DISCONNECT_SUCCESS),
        map((action: fromStomp.DisconnectSuccessAction) => action.payload),
        skipWhile((payload: { reconnect: boolean }) => !payload.reconnect),
        map(() => new fromStomp.ConnectAction()),
    );

    @Effect() disconnectFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.DISCONNECT_FAILURE),
        map(() => this.alert.disconnectFailureAlert())
    );

    @Effect() subscribe = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.SUBSCRIBE),
        map((action: fromStomp.SubscribeAction) => action.payload),
        mergeMap((payload: { channel: string, handle: () => Observable<any> }) =>
            this.stomp.subscribe(payload.channel, payload.handle).pipe(
                map((subscription: StompSubscription) => new fromStomp.SubscribeSuccessAction({ channel: payload.channel, subscription })),
                catchError((response) => scheduled([new fromStomp.SubscribeFailureAction({ channel: payload.channel, response })], asap))
            )
        )
    );

    @Effect() unsubscribe = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE),
        map((action: fromStomp.UnsubscribeAction) => action),
        withLatestFrom(this.store),
        skipWhile(([action, store]) => !store.stomp.subscriptions.has(action.payload.channel)),
        switchMap(([action, store]) =>
            scheduled([store.stomp.subscriptions.get(action.payload.channel).unsubscribe()], asap).pipe(
                map(() => new fromStomp.UnsubscribeSuccessAction({ channel: action.payload.channel })),
                catchError((response) => scheduled([new fromStomp.UnsubscribeFailureAction({ response })], asap))
            )
        )
    );

    @Effect() unsubscribeFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE_FAILURE),
        map(() => this.alert.unsubscribeFailureAlert())
    );

    @Effect() init = defer(() => {
        return scheduled([new fromStomp.ConnectAction()], asap);
    });

}
