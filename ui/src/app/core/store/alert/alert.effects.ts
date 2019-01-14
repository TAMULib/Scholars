import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { map, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../';

import { Alert, AlertLocation } from './alert.model';

import { selectAlertsByLocation } from './';

import * as fromAlerts from '../alert/alert.actions';
import * as fromDialog from '../dialog/dialog.actions';

@Injectable()
export class AlertEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>
    ) {

    }

    @Effect({ dispatch: false }) setAlertTimer = this.actions.pipe(
        ofType(fromAlerts.AlertActionTypes.ADD_ALERT),
        map((action: fromAlerts.AddAlertAction) => action.payload),
        map((payload: { alert: Alert }) => payload.alert),
        map((alert: Alert) => {
            if (alert.timer !== undefined) {
                setTimeout(() => {
                    this.store.dispatch(new fromAlerts.DismissAlertAction({ alert }));
                }, alert.timer);
            }
        })
    );

    @Effect({ dispatch: false }) dismissDialogAlerts = this.actions.pipe(
        ofType(fromDialog.DialogActionTypes.DIALOG_CLOSED),
        withLatestFrom(this.store.select(selectAlertsByLocation(AlertLocation.DIALOG))),
        map(([action, alerts]) => {
            alerts.forEach(alert => {
                this.store.dispatch(new fromAlerts.DismissAlertAction({ alert }));
            });
        })
    );

}
