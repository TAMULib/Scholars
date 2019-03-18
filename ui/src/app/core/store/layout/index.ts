import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromLayout from './layout.reducer';

export const selectLayoutState = createFeatureSelector<fromLayout.LayoutState>('layout');

export const selectWindowDimensions = createSelector(selectLayoutState, fromLayout.getWindowDimensions);

export const selectIsNavbarCollapsed = createSelector(selectLayoutState, fromLayout.isNavbarCollapsed);
export const selectIsNavigationCollapsed = createSelector(selectLayoutState, fromLayout.isNavigationCollapsed);
export const selectIsSidebarCollapsed = createSelector(selectLayoutState, fromLayout.isSidebarCollapsed);

export const selectIsNavbarExpanded = createSelector(selectLayoutState, fromLayout.isNavbarExpanded);
export const selectIsNavigationExpanded = createSelector(selectLayoutState, fromLayout.isNavigationExpanded);
export const selectIsSidebarExpanded = createSelector(selectLayoutState, fromLayout.isSidebarExpanded);
