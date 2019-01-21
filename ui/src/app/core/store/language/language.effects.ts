import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Store, select } from '@ngrx/store';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of } from 'rxjs';
import { map, switchMap, catchError, withLatestFrom } from 'rxjs/operators';

import { AlertService } from '../../service/alert.service';

import { AppState } from '../';

import { selectDefaultLanguage } from './';

import * as fromLanguage from './language.actions';

@Injectable()
export class LanguageEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private translate: TranslateService,
        private alert: AlertService
    ) {

    }

    @Effect() setLanguage = this.actions.pipe(
        ofType(fromLanguage.LanguageActionTypes.SET_LANGUAGE),
        map((action: fromLanguage.SetLanguageAction) => action.payload),
        switchMap((payload: { language: string }) =>
            this.translate.use(payload.language).pipe(
                map((result: any) => new fromLanguage.SetLanguageSuccessAction({ result })),
                catchError((error: any) => of(new fromLanguage.SetLanguageFailureAction({ error })))
            )
        )
    );

    @Effect() resetLanguage = this.actions.pipe(
        ofType(fromLanguage.LanguageActionTypes.RESET_LANGUAGE),
        withLatestFrom(this.store.pipe(select(selectDefaultLanguage))),
        switchMap(([action, language]) =>
            this.translate.use(language).pipe(
                map((result: any) => new fromLanguage.SetLanguageSuccessAction({ result })),
                catchError((error: any) => of(new fromLanguage.SetLanguageFailureAction({ error })))
            )
        )
    );

    @Effect() setLanguageSuccess = this.actions.pipe(
        ofType(fromLanguage.LanguageActionTypes.SET_LANGUAGE_SUCCESS),
        map((action: fromLanguage.SetLanguageSuccessAction) => this.alert.setLanguageSuccessAlert(action.payload))
    );

    @Effect() setLanguageFailure = this.actions.pipe(
        ofType(fromLanguage.LanguageActionTypes.SET_LANGUAGE_FAILURE),
        map((action: fromLanguage.SetLanguageFailureAction) => this.alert.setLanguageFailureAlert(action.payload))
    );

}
