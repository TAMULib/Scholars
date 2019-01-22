import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AlertLocation, AlertType } from '../store/alert';

import * as fromAlert from '../store/alert/alert.actions';
import * as fromSdr from '../store/sdr/sdr.actions';

@Injectable({
    providedIn: 'root',
})
export class AlertService {

    constructor(private translate: TranslateService) {

    }

    // NOTE: using translate.instant requires the translation json be loaded before

    public setLanguageSuccessAlert(payload: { language: string }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('LANGUAGE.SET.SUCCESS', { language: payload.language }), true, 10000);
    }

    public setLanguageFailureAlert(payload: { error: any, language: string }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, this.translate.instant('LANGUAGE.SET.FAILURE', { language: payload.language }), true, 15000);
    }

    public loginSuccessAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.LOGIN_SUCCESS'), true, 10000);
    }

    public loginFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.DIALOG, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public submitRegistrationSuccessAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.SUBMIT_REGISTRATION_SUCCESS'), true, 15000);
    }

    public submitRegistrationFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.DIALOG, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public confirmRegistrationSuccessAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.DIALOG, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.CONFIRM_REGISTRATION_SUCCESS'), false);
    }

    public confirmRegistrationFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public completeRegistrationSuccessAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.COMPLETE_REGISTRATION_SUCCESS'), true, 15000);
    }

    public completeRegistrationFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.DIALOG, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public unauthorizedAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, this.translate.instant('SHARED.ALERT.UNAUTHORIZED'), true, 15000);
    }

    public forbiddenAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.DIALOG, AlertType.WARNING, this.translate.instant('SHARED.ALERT.FORBIDDEN'), false);
    }

    public connectFailureAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, this.translate.instant('SHARED.ALERT.FAILED'), true, 15000);
    }

    public disconnectFailureAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, this.translate.instant('SHARED.ALERT.FAILED'), true, 15000);
    }

    public unsubscribeFailureAlert(): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, this.translate.instant('SHARED.ALERT.FAILED'), true, 15000);
    }

    public loadActiveThemeFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public applyActiveThemeFailureAlert(payload: { error: string }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.error, true, 15000);
    }

    public pageFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public postSuccessAlert(action: fromSdr.PostResourceSuccessAction): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.POST_SUCCESS'), true, 10000);
    }

    public postFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public putSuccessAlert(action: fromSdr.PutResourceSuccessAction): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.PUT_SUCCESS'), true, 10000);
    }

    public putFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public patchSuccessAlert(action: fromSdr.PatchResourceSuccessAction): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.PATCH_SUCCESS'), true, 10000);
    }

    public patchFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public deleteSuccessAlert(action: fromSdr.DeleteResourceSuccessAction): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.SUCCESS, this.translate.instant('SHARED.ALERT.DELETE_SUCCESS'), true, 10000);
    }

    public deleteFailureAlert(payload: { response: any }): fromAlert.AddAlertAction {
        return this.alert(AlertLocation.MAIN, AlertType.DANGER, payload.response.error, true, 15000);
    }

    public alert(location: AlertLocation, type: AlertType, message: string, dismissible: boolean, timer?: number): fromAlert.AddAlertAction {
        return new fromAlert.AddAlertAction({ alert: { location, type, message, dismissible, timer } });
    }

}
