import { Action } from '@ngrx/store';
import { SidebarMenu } from './sidebar.model';

export enum SidebarActionTypes {
    LOAD_SIDEBAR = '[Sidebar] load',
    UNLOAD_SIDEBAR = '[Sidebar] unload'
}

export class LoadSidebarAction implements Action {
    readonly type = SidebarActionTypes.LOAD_SIDEBAR;
    constructor(public payload: { menu: SidebarMenu }) { }
}

export class UnloadSidebarAction implements Action {
    readonly type = SidebarActionTypes.UNLOAD_SIDEBAR;
}

export type SidebarActions =
    LoadSidebarAction |
    UnloadSidebarAction;
