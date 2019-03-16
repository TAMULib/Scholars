import { Action } from '@ngrx/store';
import { SidebarMenu } from '../../model/sidebar';

export enum SidebarActionTypes {
    LOAD_SIDEBAR = '[Sidebar] load',
    UNLOAD_SIDEBAR = '[Sidebar] unload',
    OPEN_SIDEBAR = '[Sidebar] open',
    CLOSE_SIDEBAR = '[Sidebar] close',
    TOGGLE_COLLAPSIBLE_SECTION = '[Sidebar] toggle collapsible section'
}

export class LoadSidebarAction implements Action {
    readonly type = SidebarActionTypes.LOAD_SIDEBAR;
    constructor(public payload: { menu: SidebarMenu }) { }
}

export class UnloadSidebarAction implements Action {
    readonly type = SidebarActionTypes.UNLOAD_SIDEBAR;
}

export class OpenSidebarAction implements Action {
    readonly type = SidebarActionTypes.OPEN_SIDEBAR;
}

export class CloseSidebarAction implements Action {
    readonly type = SidebarActionTypes.CLOSE_SIDEBAR;
}

export class ToggleCollapsibleSectionAction implements Action {
    readonly type = SidebarActionTypes.TOGGLE_COLLAPSIBLE_SECTION;
    constructor(public payload: { sectionIndex: number }) { }
}

export type SidebarActions =
    LoadSidebarAction |
    UnloadSidebarAction |
    OpenSidebarAction |
    CloseSidebarAction |
    ToggleCollapsibleSectionAction;
