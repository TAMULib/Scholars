import { ThemeActions, ThemeActionTypes } from './theme.actions';

import { Theme } from '../../model/theme';
import { SafeStyle } from '@angular/platform-browser';

export type ThemeState = Readonly<{
    loadingActive: boolean;
    applyingActive: boolean;
    active: Theme;
    style: SafeStyle;
    error: any;
}>;

export const initialState: ThemeState = {
    loadingActive: false,
    applyingActive: false,
    active: undefined,
    style: undefined,
    error: undefined
};

export function reducer(state = initialState, action: ThemeActions): ThemeState {
    switch (action.type) {
        case ThemeActionTypes.LOAD_ACTIVE:
            return {
                ...state,
                loadingActive: true,
                error: undefined
            };
        case ThemeActionTypes.LOAD_ACTIVE_SUCCESS:
            return {
                ...state,
                loadingActive: false,
                active: action.payload.theme,
                error: undefined
            };
        case ThemeActionTypes.LOAD_ACTIVE_FAILURE:
            console.error(action);
            return {
                ...state,
                loadingActive: false,
                error: action.payload.response.error
            };
        case ThemeActionTypes.APPLY_ACTIVE:
            return {
                ...state,
                applyingActive: true,
                error: undefined
            };
        case ThemeActionTypes.APPLY_ACTIVE_SUCCESS:
            return {
                ...state,
                applyingActive: false,
                style: action.payload.style,
                error: undefined
            };
        case ThemeActionTypes.APPLY_ACTIVE_FAILURE:
            console.error(action);
            return {
                ...state,
                applyingActive: false,
                error: action.payload.error
            };
        default:
            return state;
    }
}

export const isLoadingActiveTheme = (state: ThemeState) => state.loadingActive;
export const getActiveTheme = (state: ThemeState) => state.active;
export const getStyle = (state: ThemeState) => state.style;
export const getError = (state: ThemeState) => state.error;
