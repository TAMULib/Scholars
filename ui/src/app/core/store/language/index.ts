import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromLanguage from './language.reducer';

export const selectLanguageState = createFeatureSelector<fromLanguage.LanguageState>('language');

export const selectGetLanguage = createSelector(selectLanguageState, fromLanguage.getLanguage);
export const selectGetDefaultLanguage = createSelector(selectLanguageState, fromLanguage.getDefaultLanguage);
