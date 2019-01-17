import { Action } from '@ngrx/store';
import { Alert } from './';

export enum AlertActionTypes {
    ADD_ALERT = '[Alert] add alert',
    DISMISS_ALERT = '[Alert] dismiss alert'
}

export class AddAlertAction implements Action {
    readonly type = AlertActionTypes.ADD_ALERT;
    constructor(public payload: { alert: Alert }) { }
}

export class DismissAlertAction implements Action {
    readonly type = AlertActionTypes.DISMISS_ALERT;
    constructor(public payload: { alert: Alert }) { }
}

export type AlertActions =
    AddAlertAction |
    DismissAlertAction;
