import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { map } from 'rxjs/operators';

import { AppState } from '../';

import { Alert } from '../../model/alert';

import * as fromAlert from '../alert/alert.actions';

@Injectable()
export class AlertEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>
    ) {

    }

    @Effect({ dispatch: false }) setAlertTimer = this.actions.pipe(
        ofType(fromAlert.AlertActionTypes.ADD_ALERT),
        map((action: fromAlert.AddAlertAction) => action.payload),
        map((payload: { alert: Alert }) => payload.alert),
        map((alert: Alert) => {
            if (alert.timer !== undefined) {
                setTimeout(() => {
                    this.store.dispatch(new fromAlert.DismissAlertAction({ alert }));
                }, alert.timer);
            }
        })
    );

}
