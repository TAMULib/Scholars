import { LayoutActions, LayoutActionTypes } from './layout.actions';

export interface WindowDimensions {
    width: number;
    height: number;
}

export type LayoutState = Readonly<{
    windowDimensions: WindowDimensions;
    navbarCollapsed: boolean;
    navigationCollapsed: boolean;
    sidebarOpen: boolean;
}>;

export const initialState: LayoutState = {
    windowDimensions: {
        width: 992,
        height: 768
    },
    navbarCollapsed: true,
    navigationCollapsed: true,
    sidebarOpen: true
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
                sidebarOpen: !state.sidebarOpen
            };
        case LayoutActionTypes.OPEN_SIDEBAR:
            return {
                ...state,
                sidebarOpen: true
            };
        case LayoutActionTypes.CLOSE_SIDEBAR:
            return {
                ...state,
                sidebarOpen: false
            };
        default:
            return state;
    }
}

export const getWindowDimensions = (state: LayoutState) => state.windowDimensions;

export const isNavbarCollapsed = (state: LayoutState) => state.navbarCollapsed;
export const isNavigationCollapsed = (state: LayoutState) => state.navigationCollapsed;
export const isSidebarOpen = (state: LayoutState) => state.sidebarOpen;
