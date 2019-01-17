import { Injectable } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of, defer } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { ThemeService } from '../../service/theme.service';

import { AlertLocation, AlertType } from '../alert';
import { Theme } from '../../model/theme';

import * as fromThemes from './theme.actions';
import * as fromAlerts from '../alert/alert.actions';

@Injectable()
export class ThemeEffects {

    constructor(private actions: Actions, private themeService: ThemeService) {

    }

    @Effect() loadActiveTheme = this.actions.pipe(
        ofType(fromThemes.ThemeActionTypes.LOAD_ACTIVE),
        switchMap(() =>
            this.themeService.getActiveTheme().pipe(
                map((theme: Theme) => new fromThemes.LoadActiveThemeSuccessAction({ theme })),
                catchError((response) => of(new fromThemes.LoadActiveThemeFailureAction({ response })))
            )
        )
    );

    @Effect() loadActiveThemeSuccess = this.actions.pipe(
        ofType(fromThemes.ThemeActionTypes.LOAD_ACTIVE_SUCCESS),
        map((action: fromThemes.LoadActiveThemeSuccessAction) => action.payload.theme),
        switchMap((theme: Theme) =>
            this.themeService.applyActiveTheme(theme).pipe(
                map((style: SafeStyle) => new fromThemes.ApplyActiveThemeSuccessAction({ style })),
                catchError((error) => of(new fromThemes.ApplyActiveThemeFailureAction({ error })))
            )
        )
    );

    @Effect() loadActiveThemeFailure = this.actions.pipe(
        ofType(fromThemes.ThemeActionTypes.LOAD_ACTIVE_FAILURE),
        map((action: fromThemes.LoadActiveThemeFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.message,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() applyActiveThemeFailure = this.actions.pipe(
        ofType(fromThemes.ThemeActionTypes.APPLY_ACTIVE_FAILURE),
        map((action: fromThemes.ApplyActiveThemeFailureAction) => action.payload),
        map((payload: { error: string }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.error,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() init = defer(() => {
        return of(new fromThemes.LoadActiveThemeAction());
    });

}
