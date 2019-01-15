import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { concat, defer, of } from 'rxjs';
import { catchError, map, switchMap, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../../store';
import { AlertLocation, AlertType } from '../alert/alert.model';

import { StompService } from '../../../core/service/stomp.service';

import * as fromStomp from './stomp.actions';
import * as fromAlerts from '../alert/alert.actions';
import * as fromAuth from '../auth/auth.actions';

@Injectable()
export class StompEffects {

    private public = '/queue/public';

    private callbacks: Map<string, Function>;

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private stomp: StompService
    ) {
        this.callbacks = new Map<string, Function>();
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
        switchMap((action: fromStomp.StompActions) =>
            this.stomp.connect().pipe(
                map((connected: boolean) => new fromStomp.ConnectSuccessAction()),
                catchError((response) => of(new fromStomp.ConnectFailureAction(response)))
            )
        )
    );

    @Effect() connectSuccess = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.CONNECT_SUCCESS),
        map((action: fromStomp.StompActions) => new fromStomp.SubscribeAction(this.public))
    );

    @Effect() connectFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.CONNECT_FAILURE),
        map((action: fromStomp.StompActions) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: action.payload.response.error.message,
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
            state.stomp.subscriptions.forEach((subscription: any, channel: string) => {
                observables.push(this.stomp.unsubscribe(subscription));
            });
            observables.push(this.stomp.disconnect());
            return concat(observables).pipe(
                map((disconnected: boolean) => new fromStomp.DisconnectSuccessAction()),
                catchError((response) => of(new fromStomp.DisconnectFailureAction(response)))
            );
        })
    );

    @Effect() disconnectFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.DISCONNECT_FAILURE),
        map((action: fromStomp.StompActions) => new fromAlerts.AddAlertAction({
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
        map((action: fromStomp.StompActions) => action.payload),
        switchMap((channel: string) =>
            this.stomp.subscribe(channel, this.callback(channel)).pipe(
                map((subscription: any) => new fromStomp.SubscribeSuccessAction({ channel, subscription })),
                catchError((response) => of(new fromStomp.SubscribeFailureAction(response)))
            )
        )
    );

    @Effect() unsubscribe = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE),
        map((action: fromStomp.StompActions) => action.payload),
        switchMap((channel: string) =>
            this.stomp.unsubscribe(this.callbacks.get(channel)).pipe(
                map((unsubscribed: boolean) => new fromStomp.UnsubscribeSuccessAction(channel)),
                catchError((response) => of(new fromStomp.UnsubscribeFailureAction(response)))
            )
        )
    );

    @Effect({ dispatch: false }) unsubscribeSuccess = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE_SUCCESS),
        map((action: fromStomp.StompActions) => {
            this.callbacks.delete(action.payload);
        })
    );

    @Effect() unsubscribeFailure = this.actions.pipe(
        ofType(fromStomp.StompActionTypes.UNSUBSCRIBE_FAILURE),
        map((action: fromStomp.StompActions) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: action.payload.response.error.message,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() init = defer(() => {
        return of(new fromStomp.ConnectAction());
    });

    private callback = (channel: string): Function => {
        this.callbacks.set(channel, (message: any) => {
            console.log(channel, message);
            switch (channel) {
                case this.public:
                    break;
                default: break;
            }
        });
        return this.callbacks.get(channel);
    }

}
