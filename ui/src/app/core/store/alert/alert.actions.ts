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

export class AddMainErrorAlertAction extends AddAlertAction {
    constructor(payload: any) {
        super({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: (payload === undefined) ? 'Unknown error!' :
                    (payload.response.name === 'HttpErrorResponse') ? 'Unable to find scholars middleware' :
                        (typeof payload.response.error === 'string') ? payload.response.error :
                            (typeof payload.error === 'string') ? payload.error : 'Unknwon error!',
                dismissible: (payload === undefined) ? false :
                    (payload.response.name === 'HttpErrorResponse') ? false :
                        (typeof payload.response.error === 'string') ? true :
                            (typeof payload.error === 'string') ? true : false,
                timer: (payload === undefined) ? 30000 :
                    (payload.response.name === 'HttpErrorResponse') ? 30000 :
                        (typeof payload.response.error === 'string') ? 15000 :
                            (typeof payload.error === 'string') ? 15000 : 30000
            }
        });
    }
}

export class AddMainSuccessAlertAction extends AddAlertAction {
    constructor(message: string) {
        super({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.SUCCESS,
                message: message,
                dismissible: true,
                timer: 10000
            }
        });
    }
}

export type AlertActions =
    AddAlertAction |
    DismissAlertAction;
