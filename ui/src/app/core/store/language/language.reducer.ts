import { LanguageActions, LanguageActionTypes } from './language.actions';

import { environment } from '../../../../environments/environment';

export type LanguageState = Readonly<{
    value: string;
    default: string;
}>;

export const initialState: LanguageState = {
    value: environment.language,
    default: environment.language
};

export function reducer(state = initialState, action: LanguageActions): LanguageState {
    switch (action.type) {
        case LanguageActionTypes.SET_LANGUAGE:
            return {
                ...state,
                value: action.payload.language
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
