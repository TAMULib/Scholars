import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { map } from 'rxjs/operators';

import { Dialog } from './dialog.model';

import * as fromDialog from './dialog.actions';

@Injectable()
export class DialogEffects {

    constructor(
        private actions: Actions,
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

}
