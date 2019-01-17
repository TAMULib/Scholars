import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import { SdrResource } from '../../model/sdr';

import * as fromSdr from './sdr.reducer';

export const selectSdrState = <R extends SdrResource>(name: string) => createFeatureSelector<fromSdr.SdrState<R>>(name);

export const selectReousrceIds = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectIds(name));
export const selectReousrceEntities = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectEntities(name));
export const selectAllResources = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectAll(name));
export const selectReousrcesTotal = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectTotal(name));

export const selectReousrceError = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getError);
export const selectReousrceIsLoading = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.isLoading);
export const selectReousrceIsUpdating = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.isUpdating);

export const selectReousrcesPage = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getPage);
export const selectReousrcesLinks = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getLinks);
