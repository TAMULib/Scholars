import { SidebarActions, SidebarActionTypes } from './sidebar.actions';
import { SidebarMenu } from './sidebar.model';

export type SidebarState = Readonly<{
    menu: SidebarMenu;
}>;

export const initialState: SidebarState = {
    menu: undefined
};

export function reducer(state = initialState, action: SidebarActions): SidebarState {
    switch (action.type) {
        case SidebarActionTypes.LOAD_SIDEBAR:
            return {
                ...state,
                menu: action.payload.menu
            };
        case SidebarActionTypes.UNLOAD_SIDEBAR:
            return {
                ...state,
                menu: undefined
            };
        default:
            return state;
    }
}

export const getMenu = (state: SidebarState) => state.menu;
