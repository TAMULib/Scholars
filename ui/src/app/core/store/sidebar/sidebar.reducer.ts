import { SidebarActions, SidebarActionTypes } from './sidebar.actions';
import { SidebarMenu } from '../../model/sidebar';

export type SidebarState = Readonly<{
    menu: SidebarMenu;
}>;

export const initialState: SidebarState = {
    menu: {
        sections: []
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
                    sections: []
                }
            };
        case SidebarActionTypes.TOGGLE_COLLAPSIBLE_SECTION:
            const collapsed = state.menu.sections[action.payload.sectionIndex].collapsed;
            state.menu.sections[action.payload.sectionIndex].collapsed = !collapsed;
            return state;
        default:
            return state;
    }
}

export const getMenu = (state: SidebarState) => state.menu;
export const hasMenu = (state: SidebarState) => state.menu.sections.length > 0;
