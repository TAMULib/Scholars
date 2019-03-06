import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { map, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../';
import { Dialog } from '../../model/dialog';
import { AlertLocation } from '../../model/alert';

import { selectAlertsByLocation } from '../alert';

import * as fromDialog from './dialog.actions';
import * as fromAlert from '../alert/alert.actions';

@Injectable()
export class DialogEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private modalService: NgbModal
    ) {

    }

    @Effect() openDialog = this.actions.pipe(
        ofType(fromDialog.DialogActionTypes.OPEN_DIALOG),
        map((action: fromDialog.OpenDialogAction) => action.payload),
        map((payload: { dialog: Dialog }) => payload.dialog),
        map((dialog: Dialog) => {
            const modal = this.modalService.open(dialog.ref.component, dialog.options);
            for (const key in dialog.ref.inputs) {
                if (dialog.ref.inputs.hasOwnProperty(key)) {
                    modal.componentInstance[key] = dialog.ref.inputs[key];
                }
            }
            return new fromDialog.DialogOpenedAction();
        })
    );

    @Effect() closeDialog = this.actions.pipe(
        ofType(fromDialog.DialogActionTypes.CLOSE_DIALOG),
        map(() => {
            this.modalService.dismissAll();
            return new fromDialog.DialogClosedAction();
        })
    );

    @Effect({ dispatch: false }) dismissDialogAlerts = this.actions.pipe(
        ofType(fromDialog.DialogActionTypes.DIALOG_CLOSED),
        withLatestFrom(this.store.select(selectAlertsByLocation(AlertLocation.DIALOG))),
        map(([action, alerts]) => {
            alerts.forEach(alert => {
                this.store.dispatch(new fromAlert.DismissAlertAction({ alert }));
            });
        })
    );

}
