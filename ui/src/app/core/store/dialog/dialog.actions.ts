import { Action } from '@ngrx/store';
import { Dialog } from './dialog.model';

export enum DialogActionTypes {
    OPEN_DIALOG = '[Dialog] open',
    DIALOG_OPENED = '[Dialog] opened',
    CLOSE_DIALOG = '[Dialog] close',
    DIALOG_CLOSED = '[Dialog] closed'
}

export class OpenDialogAction implements Action {
    readonly type = DialogActionTypes.OPEN_DIALOG;
    constructor(public payload: { dialog: Dialog }) { }
}

export class DialogOpenedAction implements Action {
    readonly type = DialogActionTypes.DIALOG_OPENED;
}

export class CloseDialogAction implements Action {
    readonly type = DialogActionTypes.CLOSE_DIALOG;
}

export class DialogClosedAction implements Action {
    readonly type = DialogActionTypes.DIALOG_CLOSED;
}


export type DialogActions =
    OpenDialogAction |
    DialogOpenedAction |
    CloseDialogAction |
    DialogClosedAction;
