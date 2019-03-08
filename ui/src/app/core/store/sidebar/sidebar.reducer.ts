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
        case SidebarActionTypes.TOGGLE_COLLAPSABLE_SECTION:
            const newState = {...state};
            const collapsable = newState.menu.sections[action.payload.sectionIndex].collapsable;
            newState.menu.sections[action.payload.sectionIndex].collapsable = {
                allowed: collapsable.allowed,
                collapsed: !collapsable.collapsed
            };
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
