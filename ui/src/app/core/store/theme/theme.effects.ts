import { Injectable } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { defer, scheduled } from 'rxjs';
import { asap } from 'rxjs/internal/scheduler/asap';
import { catchError, map, switchMap } from 'rxjs/operators';

import { AlertService } from '../../service/alert.service';
import { ThemeService } from '../../service/theme.service';

import { Theme } from '../../model/theme';

import * as fromTheme from './theme.actions';

@Injectable()
export class ThemeEffects {

    constructor(
        private actions: Actions,
        private themeService: ThemeService,
        private alert: AlertService
    ) {

    }

    @Effect() loadActiveTheme = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.LOAD_ACTIVE),
        switchMap(() =>
            this.themeService.getActiveTheme().pipe(
                map((theme: Theme) => new fromTheme.LoadActiveThemeSuccessAction({ theme })),
                catchError((response) => scheduled([new fromTheme.LoadActiveThemeFailureAction({ response })], asap))
            )
        )
    );

    @Effect() loadActiveThemeSuccess = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.LOAD_ACTIVE_SUCCESS),
        map((action: fromTheme.LoadActiveThemeSuccessAction) => action.payload.theme),
        switchMap((theme: Theme) =>
            this.themeService.applyActiveTheme(theme).pipe(
                map((style: SafeStyle) => new fromTheme.ApplyActiveThemeSuccessAction({ style })),
                catchError((error) => scheduled([new fromTheme.ApplyActiveThemeFailureAction({ error })], asap))
            )
        )
    );

    @Effect() loadActiveThemeFailure = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.LOAD_ACTIVE_FAILURE),
        map((action: fromTheme.LoadActiveThemeFailureAction) => this.alert.loadActiveThemeFailureAlert(action.payload))
    );

    @Effect() applyActiveThemeFailure = this.actions.pipe(
        ofType(fromTheme.ThemeActionTypes.APPLY_ACTIVE_FAILURE),
        map((action: fromTheme.ApplyActiveThemeFailureAction) => this.alert.applyActiveThemeFailureAlert(action.payload))
    );

    @Effect() init = defer(() => {
        return scheduled([new fromTheme.LoadActiveThemeAction()], asap);
    });

}
