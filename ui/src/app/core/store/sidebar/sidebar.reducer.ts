import { SidebarActions, SidebarActionTypes } from './sidebar.actions';
import { SidebarMenu } from '../../model/sidebar';

export type SidebarState = Readonly<{
    menu: SidebarMenu;
}>;

export const initialState: SidebarState = {
    menu: {
        sections: [],
        open: false
    }
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
                menu: {
                    sections: [],
                    open: false
                }
            };
        case SidebarActionTypes.OPEN_SIDEBAR:
            state.menu.open = true;
            return state;
        case SidebarActionTypes.CLOSE_SIDEBAR:
            state.menu.open = false;
            return state;
        case SidebarActionTypes.TOGGLE_COLLAPSIBLE_SECTION:
            const collapsed = state.menu.sections[action.payload.sectionIndex].collapsed;
            state.menu.sections[action.payload.sectionIndex].collapsed = !collapsed;
            return state;
        default:
            return state;
    }
}

export const getMenu = (state: SidebarState) => state.menu;
export const isMenuOpen = (state: SidebarState) => state.menu.open;
