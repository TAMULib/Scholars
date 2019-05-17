import { EntityState, createEntityAdapter } from '@ngrx/entity';

import { SdrActionTypes, SdrActions, getSdrAction } from './sdr.actions';
import { SdrResource, SdrPage, SdrCollectionLinks, SdrFacet } from '../../model/sdr';

import { ResourceView, CollectionView, DisplayView } from '../../model/view';

import { keys } from '../../model/repos';

import { environment } from '../../../../environments/environment';

import * as doT from 'dot';

export interface SdrState<R extends SdrResource> extends EntityState<R> {
    page: SdrPage;
    facets: SdrFacet[];
    count: number;
    links: SdrCollectionLinks;
    counting: boolean;
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
        count: undefined,
        links: undefined,
        counting: false,
        loading: false,
        updating: false,
        error: undefined
    });
};

export const getSdrReducer = <R extends SdrResource>(name: string) => {
    const getTemplateFunction = (template: string) => (resource: any) => {
        if (resource.uri !== undefined) {
            resource.uri = resource.uri[0].replace('http://hdl.handle.net/', '');
        }
        resource.vivoUrl = environment.vivoUrl;
        const templateFunction = doT.template(template);
        return templateFunction(resource);
    };
    const getResourceViewTemplateFunction = (view: ResourceView, template: string) => (resource: any) => {
        resource.collection = view.collection;
        const templateFunction = getTemplateFunction(template);
        return templateFunction(resource);
    };
    const augmentCollectionViewTemplates = (view: CollectionView) => {
        view.templateFunctions = {};
        for (const k in view.templates) {
            if (view.templates.hasOwnProperty(k)) {
                const template = view.templates[k];
                view.templateFunctions[k] = getResourceViewTemplateFunction(view, template);
            }
        }
    };
    const augmentDisplayViewTemplates = (view: DisplayView) => {
        if (view.mainContentTemplate && view.mainContentTemplate.length > 0) {
            view.mainContentTemplateFunction = doT.template(view.mainContentTemplate);
        }
        if (view.leftScanTemplate && view.leftScanTemplate.length > 0) {
            view.leftScanTemplateFunction = doT.template(view.leftScanTemplate);
        }
        if (view.rightScanTemplate && view.rightScanTemplate.length > 0) {
            view.rightScanTemplateFunction = doT.template(view.rightScanTemplate);
        }
        view.tabs.forEach(tab => {
            tab.sections.forEach(section => {
                section.templateFunction = getTemplateFunction(section.template);
            });
        });
        view.metaTemplateFunctions = {};
        for (const k in view.metaTemplates) {
            if (view.metaTemplates.hasOwnProperty(k)) {
                const template = view.metaTemplates[k];
                view.metaTemplateFunctions[k] = getTemplateFunction(template);
            }
        }
    };
    const getResources = (action: SdrActions, key: string): R[] => {
        const resources = action.payload.collection._embedded !== undefined ? action.payload.collection._embedded[key] : [];
        switch (key) {
            case 'directoryViews':
            case 'discoveryViews':
                resources.forEach(view => {
                    augmentCollectionViewTemplates(view);
                });
                break;
            case 'displayViews':
                resources.forEach(view => {
                    augmentDisplayViewTemplates(view);
                });
                break;
        }
        return resources;
    };
    const getResource = (action: SdrActions, key: string): R => {
        const resource = action.payload.document;
        switch (key) {
            case 'directoryViews':
            case 'discoveryViews':
                augmentCollectionViewTemplates(resource);
                break;
            case 'displayViews':
                augmentDisplayViewTemplates(resource);
                break;
        }
        return resource;
    };
    return (state = getSdrInitialState<R>(keys[name]), action: SdrActions): SdrState<R> => {
        switch (action.type) {
            case getSdrAction(SdrActionTypes.GET_ALL, name):
            case getSdrAction(SdrActionTypes.GET_ONE, name):
            case getSdrAction(SdrActionTypes.FIND_BY_TYPES_IN, name):
            case getSdrAction(SdrActionTypes.FIND_BY_ID_IN, name):
            case getSdrAction(SdrActionTypes.PAGE, name):
            case getSdrAction(SdrActionTypes.SEARCH, name):
                return {
                    ...state,
                    loading: true,
                    error: undefined
                };
            case getSdrAction(SdrActionTypes.COUNT, name):
                return {
                    ...state,
                    counting: true,
                    error: undefined
                };
            case getSdrAction(SdrActionTypes.FIND_BY_ID_IN_SUCCESS, name):
            case getSdrAction(SdrActionTypes.GET_ALL_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addAll(getResources(action, name), {
                    ...state,
                    links: action.payload.collection._links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.GET_ONE_SUCCESS, name):
            case getSdrAction(SdrActionTypes.FIND_BY_TYPES_IN_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addOne(getResource(action, name), {
                    ...state,
                    links: state.links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.PAGE_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addAll(getResources(action, name), {
                    ...state,
                    page: Object.assign(action.payload.collection.page, { number: action.payload.collection.page.number }),
                    links: action.payload.collection._links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.SEARCH_SUCCESS, name):
                return getSdrAdapter<R>(keys[name]).addAll(getResources(action, name), {
                    ...state,
                    page: Object.assign(action.payload.collection.page, { number: action.payload.collection.page.number }),
                    facets: action.payload.collection.facets,
                    links: action.payload.collection._links,
                    loading: false,
                    error: undefined
                });
            case getSdrAction(SdrActionTypes.COUNT_SUCCESS, name):
                return {
                    ...state,
                    count: action.payload.count.value,
                    counting: false
                };
            case getSdrAction(SdrActionTypes.GET_ALL_FAILURE, name):
            case getSdrAction(SdrActionTypes.GET_ONE_FAILURE, name):
            case getSdrAction(SdrActionTypes.FIND_BY_ID_IN_FAILURE, name):
            case getSdrAction(SdrActionTypes.FIND_BY_TYPES_IN_FAILURE, name):
            case getSdrAction(SdrActionTypes.PAGE_FAILURE, name):
            case getSdrAction(SdrActionTypes.SEARCH_FAILURE, name):
            case getSdrAction(SdrActionTypes.COUNT_FAILURE, name):
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
export const isCounting = <R extends SdrResource>(state: SdrState<R>) => state.counting;

export const getPage = <R extends SdrResource>(state: SdrState<R>) => state.page;
export const getCount = <R extends SdrResource>(state: SdrState<R>) => state.count;
export const getFacets = <R extends SdrResource>(state: SdrState<R>) => state.facets;
export const getLinks = <R extends SdrResource>(state: SdrState<R>) => state.links;
