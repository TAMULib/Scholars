import { LayoutActions, LayoutActionTypes } from './layout.actions';

export type LayoutState = Readonly<{
    navbarCollapsed: boolean;
    navigationCollapsed: boolean;
    sidebarCollapsed: boolean;
}>;

export const initialState: LayoutState = {
    navbarCollapsed: true,
    navigationCollapsed: true,
    sidebarCollapsed: false
};

export function reducer(state = initialState, action: LayoutActions): LayoutState {
    switch (action.type) {
        case LayoutActionTypes.TOGGLE_NAVBAR:
            return {
                ...state,
                navbarCollapsed: !state.navbarCollapsed
            };
        case LayoutActionTypes.TOGGLE_NAVIGATION:
            return {
                ...state,
                navigationCollapsed: !state.navigationCollapsed
            };
        case LayoutActionTypes.TOGGLE_SIDEBAR:
            return {
                ...state,
                sidebarCollapsed: !state.sidebarCollapsed
            };
        default:
            return state;
    }
}

export const isNavbarCollapsed = (state: LayoutState) => state.navbarCollapsed;
export const isNavigationCollapsed = (state: LayoutState) => state.navigationCollapsed;
export const isSidebarCollapsed = (state: LayoutState) => state.sidebarCollapsed;
