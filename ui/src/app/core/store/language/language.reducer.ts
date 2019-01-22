import { LanguageActions, LanguageActionTypes } from './language.actions';

import { environment } from '../../../../environments/environment';

export type LanguageState = Readonly<{
    value: string;
    default: string;
    setting: boolean;
}>;

export const initialState: LanguageState = {
    value: environment.language,
    default: environment.language,
    setting: false
};

export function reducer(state = initialState, action: LanguageActions): LanguageState {
    switch (action.type) {
        case LanguageActionTypes.SET_LANGUAGE:
            return {
                ...state,
                setting: true
            };
        case LanguageActionTypes.SET_LANGUAGE_SUCCESS:
            return {
                ...state,
                value: action.payload.language,
                setting: false
            };
        case LanguageActionTypes.SET_LANGUAGE_FAILURE:
            return {
                ...state,
                setting: false
            };
        case LanguageActionTypes.SET_DEFAULT_LANGUAGE:
            return {
                ...state,
                default: action.payload.language
            };
        case LanguageActionTypes.RESET_LANGUAGE:
            return {
                ...state,
                value: state.default
            };
        default:
            return state;
    }
}

export const getLanguage = (state: LanguageState) => state.value;
export const getDefaultLanguage = (state: LanguageState) => state.default;
export const isSettingLanguage = (state: LanguageState) => state.setting;
