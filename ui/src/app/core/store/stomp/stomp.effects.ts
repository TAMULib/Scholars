import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { defer, of } from 'rxjs';
import { catchError, map, switchMap, withLatestFrom, skipWhile } from 'rxjs/operators';

import { AppState } from '../';
import { StompSubscription } from './';

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
                catchError((response) => of(new fromStomp.ConnectFailureAction({ response })))
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
                catchError((response) => of(new fromStomp.DisconnectFailureAction({ response })))
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
        switchMap((payload: { channel: string, handle: Function }) =>
            this.stomp.subscribe(payload.channel, payload.handle).pipe(
                map((subscription: StompSubscription) => new fromStomp.SubscribeSuccessAction({ channel: payload.channel, subscription })),
                catchError((response) => of(new fromStomp.SubscribeFailureAction({ channel: payload.channel, response })))
            )
        )
    );

    @Effect() unsubscribe = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE),
        map((action: fromStomp.UnsubscribeAction) => action),
        withLatestFrom(this.store),
        skipWhile(([action, store]) => !store.stomp.subscriptions.has(action.payload.channel)),
        switchMap(([action, store]) =>
            store.stomp.subscriptions.get(action.payload.channel).unsubscribe().pipe(
                map(() => new fromStomp.UnsubscribeSuccessAction({ channel: action.payload.channel })),
                catchError((response) => of(new fromStomp.UnsubscribeFailureAction({ response })))
            )
        )
    );

    @Effect() unsubscribeFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE_FAILURE),
        map(() => this.alert.unsubscribeFailureAlert())
    );

    @Effect() init = defer(() => {
        return of(new fromStomp.ConnectAction());
    });

}
