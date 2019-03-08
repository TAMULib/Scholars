import { Action } from '@ngrx/store';
import { SidebarMenu } from '../../model/sidebar';

export enum SidebarActionTypes {
    LOAD_SIDEBAR = '[Sidebar] load',
    UNLOAD_SIDEBAR = '[Sidebar] unload',
    TOGGLE_COLLAPSABLE_SECTION = '[Sidebar] toggle collapsable section'
}

export class ToggleCollapsableSectionAction implements Action {
    readonly type = SidebarActionTypes.TOGGLE_COLLAPSABLE_SECTION;
    constructor(public payload: { sectionIndex: number }) { }
}

export class LoadSidebarAction implements Action {
    readonly type = SidebarActionTypes.LOAD_SIDEBAR;
    constructor(public payload: { menu: SidebarMenu }) { }
}

export class UnloadSidebarAction implements Action {
    readonly type = SidebarActionTypes.UNLOAD_SIDEBAR;
}

export type SidebarActions =
    ToggleCollapsableSectionAction |
    LoadSidebarAction |
    UnloadSidebarAction;
