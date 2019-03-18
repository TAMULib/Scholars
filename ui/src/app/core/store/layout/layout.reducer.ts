import { LayoutActions, LayoutActionTypes } from './layout.actions';

export interface WindowDimensions {
    width: number;
    height: number;
}

export type LayoutState = Readonly<{
    windowDimensions: WindowDimensions;
    navbarCollapsed: boolean;
    navigationCollapsed: boolean;
    sidebarCollapsed: boolean;
}>;

export const initialState: LayoutState = {
    windowDimensions: {
        width: 992,
        height: 768
    },
    navbarCollapsed: true,
    navigationCollapsed: true,
    sidebarCollapsed: false
};

export function reducer(state = initialState, action: LayoutActions): LayoutState {
    switch (action.type) {
        case LayoutActionTypes.RESIZE_WINDOW:
            return {
                ...state,
                windowDimensions: action.payload.windowDimensions
            };
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
        case LayoutActionTypes.OPEN_SIDEBAR:
            return {
                ...state,
                sidebarCollapsed: false
            };
        case LayoutActionTypes.CLOSE_SIDEBAR:
            return {
                ...state,
                sidebarCollapsed: true
            };
        default:
            return state;
    }
}

export const getWindowDimensions = (state: LayoutState) => state.windowDimensions;

export const isNavbarCollapsed = (state: LayoutState) => state.navbarCollapsed;
export const isNavigationCollapsed = (state: LayoutState) => state.navigationCollapsed;
export const isSidebarCollapsed = (state: LayoutState) => state.sidebarCollapsed;

export const isNavbarExpanded = (state: LayoutState) => !state.navbarCollapsed;
export const isNavigationExpanded = (state: LayoutState) => !state.navigationCollapsed;
export const isSidebarExpanded = (state: LayoutState) => !state.sidebarCollapsed;
