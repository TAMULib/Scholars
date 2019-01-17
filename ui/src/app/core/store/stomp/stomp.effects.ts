import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { concat, defer, of } from 'rxjs';
import { catchError, map, switchMap, withLatestFrom, skipWhile } from 'rxjs/operators';

import { AppState } from '../';
import { AlertLocation, AlertType } from '../alert';
import { StompSubscription } from './';

import { StompService } from '../../service/stomp.service';

import * as fromStomp from './stomp.actions';
import * as fromAlerts from '../alert/alert.actions';
import * as fromAuth from '../auth/auth.actions';

@Injectable()
export class StompEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private stomp: StompService
    ) {

    }

    @Effect() reconnect = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGIN_SUCCESS, fromAuth.AuthActionTypes.LOGOUT_SUCCESS),
        switchMap(() => [
            new fromStomp.DisconnectAction(),
            new fromStomp.ConnectAction()
        ])
    );

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
        map(() => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: 'Failed to connect.',
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() disconnect = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.DISCONNECT),
        withLatestFrom(this.store),
        switchMap(([action, state]) => {
            const observables = [];
            state.stomp.subscriptions.forEach((value: any, channel: string) => {
                observables.push(value.subscription.unsubscribe());
            });
            observables.push(this.stomp.disconnect());
            return concat(observables).pipe(
                map(() => new fromStomp.DisconnectSuccessAction()),
                catchError((response) => of(new fromStomp.DisconnectFailureAction({ response })))
            );
        })
    );

    @Effect() disconnectFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.DISCONNECT_FAILURE),
        map(() => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: 'Failed to disconnected.',
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() subscribe = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.SUBSCRIBE),
        map((action: fromStomp.SubscribeAction) => action.payload),
        switchMap((payload: { channel: string, handle: (message: any) => void }) =>
            this.stomp.subscribe(payload.channel, payload.handle).pipe(
                map((subscription: StompSubscription) => new fromStomp.SubscribeSuccessAction({ channel: payload.channel, subscription })),
                catchError((response) => of(new fromStomp.SubscribeFailureAction({ response })))
            )
        )
    );

    @Effect() unsubscribe = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE),
        map((action: fromStomp.UnsubscribeAction) => action),
        withLatestFrom(this.store),
        skipWhile(([action, store]) => !store.stomp.subscriptions.has(action.payload.channel)),
        switchMap(([action, store]) =>
            this.stomp.unsubscribe(store.stomp.subscriptions.get(action.payload.channel).id).pipe(
                map(() => new fromStomp.UnsubscribeSuccessAction({ channel: action.payload.channel })),
                catchError((response) => of(new fromStomp.UnsubscribeFailureAction({ response })))
            )
        )
    );

    @Effect() unsubscribeFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE_FAILURE),
        map(() => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: 'Failed to unsubscribe.',
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() init = defer(() => {
        return of(new fromStomp.ConnectAction());
    });

}
