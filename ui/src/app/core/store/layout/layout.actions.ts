import { Action } from '@ngrx/store';

export enum LayoutActionTypes {
    TOGGLE_NAVBAR = '[Layout] toggle navbar',
    TOGGLE_NAVIGATION = '[Layout] toggle navigation',
    TOGGLE_SIDEBAR = '[Layout] toggle sidebar'
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
    ToggleNavbarAction |
    ToggleNavigationAction |
    ToggleSidebarAction;
