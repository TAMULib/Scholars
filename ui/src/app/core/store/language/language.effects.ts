import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of } from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';

import * as fromLanguage from './language.actions';

@Injectable()
export class LanguageEffects {

    constructor(
        private actions: Actions,
        private translate: TranslateService
    ) {

    }

    @Effect() setLanguage = this.actions.pipe(
        ofType(fromLanguage.LanguageActionTypes.SET_LANGUAGE),
        map((action: fromLanguage.SetLanguageAction) => action.payload),
        switchMap((payload: { language: string }) =>
            this.translate.use(payload.language).pipe(
                map((response: any) => new fromLanguage.SetLanguageSuccessAction({ response })),
                catchError((response) => of(new fromLanguage.SetLanguageFailureAction({ response })))
            )
        )
    );

}
