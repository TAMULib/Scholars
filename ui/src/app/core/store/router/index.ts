import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import { RouterReducerState } from '@ngrx/router-store';
import { CustomRouterState } from './router.reducer';

export const selectRouterState = createFeatureSelector<RouterReducerState<CustomRouterState>>('router');

export const selectRouterSearchQuery = createSelector(selectRouterState, (router: RouterReducerState<CustomRouterState>) => router && router.state && router.state.queryParams ? router.state.queryParams.query : '');
