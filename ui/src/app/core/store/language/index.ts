import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromLanguage from './language.reducer';

export const selectLanguageState = createFeatureSelector<fromLanguage.LanguageState>('language');

export const selectLanguage = createSelector(selectLanguageState, fromLanguage.getLanguage);
export const selectDefaultLanguage = createSelector(selectLanguageState, fromLanguage.getDefaultLanguage);
export const selectIsSettingLanguage = createSelector(selectLanguageState, fromLanguage.isSettingLanguage);
