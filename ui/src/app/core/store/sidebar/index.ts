import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromSidebar from './sidebar.reducer';

export const selectSidebarState = createFeatureSelector<fromSidebar.SidebarState>('sidebar');

export const selectMenu = createSelector(selectSidebarState, fromSidebar.getMenu);
