import { EntityState, createEntityAdapter } from '@ngrx/entity';

import { SdrActionTypes, SdrActions, getSdrAction } from './sdr.actions';
import { SdrResource, SdrPage, SdrCollectionLinks, SdrFacet } from '../../model/sdr';

import { keys } from '../../model/repos';

export interface SdrState<R extends SdrResource> extends EntityState<R> {
    page: SdrPage;
    facets: SdrFacet[];
    links: SdrCollectionLinks;
    loading: boolean;
    updating: boolean;
    error: any;
}

export const getSdrAdapter = <R extends SdrResource>(key: string) => {
    return createEntityAdapter<R>({
        selectId: (resource: R) => resource[key],
    });
};

export const getSdrInitialState = <R extends SdrResource>(key: string) => {
    return getSdrAdapter<R>(key).getInitialState({
        page: undefined,
        facets: [],
        links: undefined,
        loading: false,
        updating: false,
        error: undefined
    });
};

export const getSdrReducer = <R extends SdrResource>(name: string) => {
    const getResources = (action: SdrActions, key: string): R[] => {
        return action.payload.collection._embedded !== undefined ? action.payload.collection._embedded[key] : [];
    };
    return (state = getSdrInitialState<R>(keys[name]), action: SdrActions): SdrState<R> => {
        switch (action.type) {
            case getSdrAction(SdrActionTypes.GET_ALL, name):
                return {
                    ...state,
                    loading: true,
                    error: undefined
                };
            case getSdrAction(SdrActionTypes.GET_ALL_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addAll(getResources(action, name), {
                    ...state,
                    links: action.payload.collection._links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.GET_ALL_FAILURE, name):
                console.error(action);
                return {
                    ...state,
                    loading: false,
                    error: action.payload.response.error
                };
            case getSdrAction(SdrActionTypes.PAGE, name):
                return {
                    ...state,
                    loading: true,
                    error: undefined
                };
            case getSdrAction(SdrActionTypes.PAGE_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addAll(getResources(action, name), {
                    ...state,
                    page: Object.assign(action.payload.collection.page, { number: action.payload.collection.page.number }),
                    links: action.payload.collection._links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.PAGE_FAILURE, name):
                console.error(action);
                return {
                    ...state,
                    loading: false,
                    error: action.payload.response.error
                };
            case getSdrAction(SdrActionTypes.SEARCH, name):
                return {
                    ...state,
                    loading: true,
                    error: undefined
                };
            case getSdrAction(SdrActionTypes.SEARCH_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addAll(getResources(action, name), {
                    ...state,
                    page: Object.assign(action.payload.collection.page, { number: action.payload.collection.page.number }),
                    facets: action.payload.collection.facets,
                    links: action.payload.collection._links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.SEARCH_FAILURE, name):
                console.error(action);
                return {
                    ...state,
                    loading: false,
                    error: action.payload.response.error
                };
            case getSdrAction(SdrActionTypes.PUT, name):
            case getSdrAction(SdrActionTypes.POST, name):
            case getSdrAction(SdrActionTypes.PATCH, name):
            case getSdrAction(SdrActionTypes.DELETE, name):
                return {
                    ...state,
                    updating: true
                };
            case getSdrAction(SdrActionTypes.PUT_SUCCESS, name):
            case getSdrAction(SdrActionTypes.POST_SUCCESS, name):
            case getSdrAction(SdrActionTypes.PATCH_SUCCESS, name):
            case getSdrAction(SdrActionTypes.DELETE_SUCCESS, name):
                // NOTE: entity in page is updated via broadcast
                return {
                    ...state,
                    updating: false
                };
            case getSdrAction(SdrActionTypes.PUT_FAILURE, name):
            case getSdrAction(SdrActionTypes.POST_FAILURE, name):
            case getSdrAction(SdrActionTypes.PATCH_FAILURE, name):
            case getSdrAction(SdrActionTypes.DELETE_FAILURE, name):
                return {
                    ...state,
                    updating: false,
                    error: action.payload.response
                };
            case getSdrAction(SdrActionTypes.CLEAR, name):
                return {
                    ...state,
                    page: undefined,
                    facets: [],
                    links: undefined,
                    loading: false,
                    updating: false,
                    error: undefined
                };
        }
        return state;
    };
};

export const selectIds = <R extends SdrResource>(name: string) => getSdrAdapter<R>(keys[name]).getSelectors().selectIds;
export const selectEntities = <R extends SdrResource>(name: string) => getSdrAdapter<R>(keys[name]).getSelectors().selectEntities;
export const selectAll = <R extends SdrResource>(name: string) => getSdrAdapter<R>(keys[name]).getSelectors().selectAll;
export const selectTotal = <R extends SdrResource>(name: string) => getSdrAdapter<R>(keys[name]).getSelectors().selectTotal;

export const getError = <R extends SdrResource>(state: SdrState<R>) => state.error;
export const isLoading = <R extends SdrResource>(state: SdrState<R>) => state.loading;
export const isUpdating = <R extends SdrResource>(state: SdrState<R>) => state.updating;

export const getPage = <R extends SdrResource>(state: SdrState<R>) => state.page;
export const getFacets = <R extends SdrResource>(state: SdrState<R>) => state.facets;
export const getLinks = <R extends SdrResource>(state: SdrState<R>) => state.links;
