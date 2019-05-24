import { createSelector, createFeatureSelector, Selector } from '@ngrx/store';

import { DiscoveryView, DisplayView, DirectoryView } from '../../model/view';
import { SdrResource } from '../../model/sdr';

import * as fromSdr from './sdr.reducer';

export const selectSdrState = <R extends SdrResource>(name: string) => createFeatureSelector<fromSdr.SdrState<R>>(name);

export const selectResourceIds = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectIds(name));
export const selectResourceEntities = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectEntities(name));
export const selectAllResources = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectAll(name));
export const selectResourcesTotal = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.selectTotal(name));

export const selectResourceError = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getError);
export const selectResourceIsCounting = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.isCounting);
export const selectResourceIsLoading = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.isLoading);
export const selectResourceIsUpdating = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.isUpdating);

export const selectResourcesCount = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getCount);
export const selectResourcesPage = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getPage);
export const selectResourcesFacets = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getFacets);
export const selectResourcesLinks = <R extends SdrResource>(name: string) => createSelector(selectSdrState<R>(name), fromSdr.getLinks);

export const selectResourceById = <R extends SdrResource>(name: string, id: string) => createSelector(
    selectResourceEntities<R>(name),
    resources => resources[id]
);

export const selectDefaultDiscoveryView = createSelector(
    selectResourceIds<DiscoveryView>('discoveryViews'),
    selectResourceEntities<DiscoveryView>('discoveryViews'),
    (ids, resources) => resources[ids[0]] ? resources[ids[0]] : undefined
);

export const selectDirectoryViewByCollection = (collection: string) => createSelector(
    selectResourceEntities<DirectoryView>('directoryViews'),
    (directoryViews) => {
        for (const key in directoryViews) {
            if (directoryViews.hasOwnProperty(key)) {
                if (directoryViews[key].collection === collection) {
                    return directoryViews[key];
                }
            }
        }
    }
);

export const selectDisplayViewByTypes = (types: string[]) => createSelector(
    selectResourceEntities<DisplayView>('displayViews'),
    selectResourceIsLoading<DisplayView>('displayViews'),
    (displayViews, isLoading) => {
        let defaultDisplayView;
        for (const key in displayViews) {
            if (displayViews.hasOwnProperty(key)) {
                for (const i in types) {
                    if (displayViews.hasOwnProperty(key)) {
                        if (displayViews[key].types.indexOf(types[i]) >= 0) {
                            return [displayViews[key], isLoading];
                        }
                    }
                }
                if (displayViews[key].name === 'Default') {
                    defaultDisplayView = displayViews[key];
                }
            }
        }
        return [defaultDisplayView, isLoading];
    }
);

export const selectDisplayViewTab = (view: string, tab: string) => createSelector(
    selectResourceEntities<DisplayView>('displayViews'),
    (displayViews) => {
        for (const i in displayViews) {
            if (displayViews.hasOwnProperty(i)) {
                if (displayViews[i].name === view) {
                    for (const j in displayViews[i].tabs) {
                        if (displayViews[i].tabs.hasOwnProperty(j)) {
                            if (displayViews[i].tabs[j].name === tab) {
                                return displayViews[i].tabs[j];
                            }
                        }
                    }
                }
            }
        }
    }
);
