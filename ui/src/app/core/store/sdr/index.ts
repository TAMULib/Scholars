import { createSelector, createFeatureSelector } from '@ngrx/store';

import { DiscoveryView, DisplayView } from '../../model/view';
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

export const selectResourcesPage = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getPage);
export const selectResourcesFacets = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getFacets);
export const selectResourcesLinks = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getLinks);

export const selectResourceById = <R extends SdrResource>(name: string, id: string) => createSelector(
    selectReousrceEntities<R>(name),
    resources => resources[id]
);

export const selectDefaultDiscoveryView = createSelector(
    selectReousrceIds<DiscoveryView>('discoveryViews'),
    selectReousrceEntities<DiscoveryView>('discoveryViews'),
    (ids, resources) => resources[ids[0]] ? resources[ids[0]] : undefined
);

export const selectDisplayViewByType = (types: string[]) => createSelector(
    selectReousrceEntities<DisplayView>('displayViews'),
    (displayViews) => {
        let defaultDisplayView;
        for (const key in displayViews) {
            if (displayViews.hasOwnProperty(key)) {
                for (const i in types) {
                    if (displayViews.hasOwnProperty(key)) {
                        if (displayViews[key].types.indexOf(types[i]) >= 0) {
                            return displayViews[key];
                        }
                    }
                }
                if (displayViews[key].name === 'Default') {
                    defaultDisplayView = displayViews[key];
                }
            }
        }
        return defaultDisplayView;
    }
);
