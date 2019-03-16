import { Action } from '@ngrx/store';
import { WindowDimensions } from './layout.reducer';

export enum LayoutActionTypes {
    RESIZE_WINDOW = '[Layout] resize window',
    TOGGLE_NAVBAR = '[Layout] toggle navbar',
    TOGGLE_NAVIGATION = '[Layout] toggle navigation',
    TOGGLE_SIDEBAR = '[Layout] toggle sidebar'
}

export class ResizeWindowAction implements Action {
    readonly type = LayoutActionTypes.RESIZE_WINDOW;
    constructor(public payload: { windowDimensions: WindowDimensions }) {

    }
}

export class ToggleNavbarAction implements Action {
    readonly type = LayoutActionTypes.TOGGLE_NAVBAR;
}

export class ToggleNavigationAction implements Action {
    readonly type = LayoutActionTypes.TOGGLE_NAVIGATION;
}

export class ToggleSidebarAction implements Action {
    readonly type = LayoutActionTypes.TOGGLE_SIDEBAR;
}

export type LayoutActions =
    ResizeWindowAction |
    ToggleNavbarAction |
    ToggleNavigationAction |
    ToggleSidebarAction;
