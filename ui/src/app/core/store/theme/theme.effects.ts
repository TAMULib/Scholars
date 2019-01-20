import { Injectable } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of, defer } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { ThemeService } from '../../service/theme.service';

import { AlertLocation, AlertType } from '../alert';
import { Theme } from '../../model/theme';

import * as fromTheme from './theme.actions';
import * as fromAlert from '../alert/alert.actions';

@Injectable()
export class ThemeEffects {

    constructor(private actions: Actions, private themeService: ThemeService) {

    }

    @Effect() loadActiveTheme = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.LOAD_ACTIVE),
        switchMap(() =>
            this.themeService.getActiveTheme().pipe(
                map((theme: Theme) => new fromTheme.LoadActiveThemeSuccessAction({ theme })),
                catchError((response) => of(new fromTheme.LoadActiveThemeFailureAction({ response })))
            )
        )
    );

    @Effect() loadActiveThemeSuccess = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.LOAD_ACTIVE_SUCCESS),
        map((action: fromTheme.LoadActiveThemeSuccessAction) => action.payload.theme),
        switchMap((theme: Theme) =>
            this.themeService.applyActiveTheme(theme).pipe(
                map((style: SafeStyle) => new fromTheme.ApplyActiveThemeSuccessAction({ style })),
                catchError((error) => of(new fromTheme.ApplyActiveThemeFailureAction({ error })))
            )
        )
    );

    @Effect() loadActiveThemeFailure = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.LOAD_ACTIVE_FAILURE),
        map((action: fromTheme.LoadActiveThemeFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlert.AddAlertAction({
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
        ofType(fromTheme.ThemeActionTypes.APPLY_ACTIVE_FAILURE),
        map((action: fromTheme.ApplyActiveThemeFailureAction) => action.payload),
        map((payload: { error: string }) => new fromAlert.AddAlertAction({
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
        return of(new fromTheme.LoadActiveThemeAction());
    });

}
