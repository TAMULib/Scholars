import { LayoutActions, LayoutActionTypes } from './layout.actions';

export interface WindowDimensions {
    height: number;
    width: number;
}

export type LayoutState = Readonly<{
    windowDimensions: WindowDimensions;
    navbarCollapsed: boolean;
    navigationCollapsed: boolean;
    sidebarCollapsed: boolean;
}>;

export const initialState: LayoutState = {
    windowDimensions: {
        height: window.innerHeight,
        width: window.innerWidth
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
        default:
            return state;
    }
}

export const getWindowDimensions = (state: LayoutState) => state.windowDimensions;

export const isNavbarCollapsed = (state: LayoutState) => state.navbarCollapsed;
export const isNavigationCollapsed = (state: LayoutState) => state.navigationCollapsed;
export const isSidebarCollapsed = (state: LayoutState) => state.sidebarCollapsed;
