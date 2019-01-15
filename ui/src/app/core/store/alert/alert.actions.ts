import { Action } from '@ngrx/store';
import { Alert, AlertLocation, AlertType } from './alert.model';

export enum AlertActionTypes {
    ADD_ALERT = '[Alert] add alert',
    DISMISS_ALERT = '[Alert] dismiss alert',
    DISMISS_ALL_ALERTS_BY_LOCATION = '[Alert] dismiss all alerts by location'
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
