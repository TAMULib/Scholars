import { SidebarActions, SidebarActionTypes } from './sidebar.actions';
import { SidebarMenu } from '../../model/sidebar';

export type SidebarState = Readonly<{
    menu: SidebarMenu;
}>;

export const initialState: SidebarState = {
    menu: undefined
};

export function reducer(state = initialState, action: SidebarActions): SidebarState {
    switch (action.type) {
        case SidebarActionTypes.TOGGLE_COLLAPSIBLE_SECTION:
            const newState = { ...state };
            const collapsed = newState.menu.sections[action.payload.sectionIndex].collapsed;
            newState.menu.sections[action.payload.sectionIndex].collapsed = !collapsed;
            return newState;
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
