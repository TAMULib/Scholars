import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';

import { ThemesActions, ThemesActionTypes } from './themes.actions';

import { Theme } from '../../model/theme';
import { SdrPage } from '../../model/sdr';
import { SdrCollectionLinks } from '../../model/sdr';
import { SafeStyle } from '@angular/platform-browser';

export interface ThemesState extends EntityState<Theme> {
    page: SdrPage;
    links: SdrCollectionLinks;
    loading: boolean;
    error: any;
    style: SafeStyle;
    applyingActive: boolean;
    active: Theme;
    loadingActive: boolean;
}

export const adapter: EntityAdapter<Theme> = createEntityAdapter<Theme>({
    selectId: (theme: Theme) => theme.name,
});

export const initialState: ThemesState = adapter.getInitialState({
    page: undefined,
    links: undefined,
    loading: false,
    error: undefined,
    style: undefined,
    applyingActive: false,
    active: undefined,
    loadingActive: false
});

export function reducer(state = initialState, action: ThemesActions): ThemesState {
    switch (action.type) {
        case ThemesActionTypes.LOAD_ACTIVE:
            return {
                ...state,
                loadingActive: true,
                error: undefined
            };
        case ThemesActionTypes.LOAD_ACTIVE_SUCCESS:
            return {
                ...state,
                loadingActive: false,
                active: action.payload.theme,
                error: undefined
            };
        case ThemesActionTypes.LOAD_ACTIVE_FAILURE:
            return {
                ...state,
                loadingActive: false,
                error: action.payload.response.error
            };
        case ThemesActionTypes.APPLY_ACTIVE:
            return {
                ...state,
                applyingActive: true,
                error: undefined
            };
        case ThemesActionTypes.APPLY_ACTIVE_SUCCESS:
            return {
                ...state,
                applyingActive: false,
                style: action.payload.style,
                error: undefined
            };
        case ThemesActionTypes.APPLY_ACTIVE_FAILURE:
            return {
                ...state,
                applyingActive: false,
                error: action.payload.error
            };
        case ThemesActionTypes.LOAD:
            return {
                ...state,
                loading: true,
                error: undefined
            };
        case ThemesActionTypes.LOAD_SUCCESS:
            return adapter.addAll(action.payload.collection._embedded.themes, {
                ...state,
                page: Object.assign(action.payload.collection.page, { number: action.payload.collection.page.number + 1 }),
                links: action.payload.collection._links,
                loading: false,
                error: undefined
            });
        case ThemesActionTypes.LOAD_FAILURE:
            return {
                ...state,
                loading: false,
                error: action.payload.response.error
            };
        case ThemesActionTypes.CLEAR:
            return {
                ...state,
                page: undefined
            };
        default:
            return state;
    }
}

const { selectIds, selectEntities, selectAll, selectTotal } = adapter.getSelectors();

export const selectThemesIds = selectIds;
export const selectThemesEntities = selectEntities;
export const selectAllThemes = selectAll;
export const selectThemesTotal = selectTotal;

export const getStyle = (state: ThemesState) => state.style;

export const isThemesLoading = (state: ThemesState) => state.loading;
export const getThemesError = (state: ThemesState) => state.error;

export const getThemesPage = (state: ThemesState) => state.page;
export const getThemesLinks = (state: ThemesState) => state.links;

export const isLoadingActiveTheme = (state: ThemesState) => state.loadingActive;
export const getActiveTheme = (state: ThemesState) => state.active;
