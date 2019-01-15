import { Injectable } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of, defer } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { ThemesService } from '../../service/themes.service';

import { AlertLocation, AlertType } from '../alert/alert.model';
import { SdrCollection } from '../../model/sdr';
import { SdrPageRequest } from '../../model/sdr/sdr-page';
import { Theme } from '../../model/theme';

import * as fromThemes from './themes.actions';
import * as fromAlerts from '../alert/alert.actions';
import * as fromAuth from '../auth/auth.actions';

@Injectable()
export class ThemesEffects {

    constructor(private actions: Actions, private themesService: ThemesService) {

    }

    @Effect() loadActiveTheme = this.actions.pipe(
        ofType(fromThemes.ThemesActionTypes.LOAD_ACTIVE),
        switchMap(() =>
            this.themesService.getActiveTheme().pipe(
                map((theme: Theme) => new fromThemes.LoadActiveThemeSuccessAction({ theme })),
                catchError((response) => of(new fromThemes.LoadActiveThemeFailureAction({ response })))
            )
        )
    );

    @Effect() loadActiveThemeSuccess = this.actions.pipe(
        ofType(fromThemes.ThemesActionTypes.LOAD_ACTIVE_SUCCESS),
        map((action: fromThemes.LoadActiveThemeSuccessAction) => action.payload.theme),
        switchMap((theme: Theme) =>
            this.themesService.applyActiveTheme(theme).pipe(
                map((style: SafeStyle) => new fromThemes.ApplyActiveThemeSuccessAction({ style })),
                catchError((error) => of(new fromThemes.ApplyActiveThemeFailureAction({ error })))
            )
        )
    );

    @Effect() loadActiveThemeFailure = this.actions.pipe(
        ofType(fromThemes.ThemesActionTypes.LOAD_ACTIVE_FAILURE),
        map((action: fromThemes.LoadActiveThemeFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() applyActiveThemeFailure = this.actions.pipe(
        ofType(fromThemes.ThemesActionTypes.APPLY_ACTIVE_FAILURE),
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

    @Effect() loadThemes = this.actions.pipe(
        ofType(fromThemes.ThemesActionTypes.LOAD),
        map((action: fromThemes.LoadThemesAction) => action.payload),
        map((payload: { page: SdrPageRequest }) => payload.page),
        switchMap((page: SdrPageRequest) =>
            this.themesService.getPage(page).pipe(
                map((collection: SdrCollection) => new fromThemes.LoadThemesSuccessAction({ collection })),
                catchError((response) => of(new fromThemes.LoadThemesFailureAction({ response })))
            )
        )
    );

    @Effect() loadThemesFailure = this.actions.pipe(
        ofType(fromThemes.ThemesActionTypes.LOAD_ACTIVE_FAILURE),
        map((action: fromThemes.LoadThemesFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() clearThemes = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGOUT_SUCCESS),
        map(() => new fromThemes.ClearThemesAction())
    );

    @Effect() init = defer(() => {
        return of(new fromThemes.LoadActiveThemeAction());
    });

}
