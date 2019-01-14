import { Action } from '@ngrx/store';

export enum LayoutActionTypes {
    TOGGLE_NAVAR = '[Layout] toggle navbar',
    TOGGLE_NAVIGATION = '[Layout] toggle navigation',
    TOGGLE_SIDEBAR = '[Layout] toggle sidebar'
}

export class ToggleNavbarAction implements Action {
    readonly type = LayoutActionTypes.TOGGLE_NAVAR;
    constructor() { }
}

export class ToggleNavigationAction implements Action {
    readonly type = LayoutActionTypes.TOGGLE_NAVIGATION;
    constructor() { }
}

export class ToggleSidebarAction implements Action {
    readonly type = LayoutActionTypes.TOGGLE_SIDEBAR;
    constructor() { }
}


export type LayoutActions =
    ToggleNavbarAction |
    ToggleNavigationAction |
    ToggleSidebarAction;
