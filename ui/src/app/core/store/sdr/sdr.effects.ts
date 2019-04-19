import { Injectable, Injector } from '@angular/core';
import { Params } from '@angular/router';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';
import { TranslateService } from '@ngx-translate/core';

import { of, combineLatest, defer, Observable } from 'rxjs';
import { map, switchMap, catchError, withLatestFrom, skipWhile, take, filter } from 'rxjs/operators';

import { AlertService } from '../../service/alert.service';
import { DialogService } from '../../service/dialog.service';

import { AppState } from '../';
import { StompState } from '../stomp/stomp.reducer';
import { CustomRouterState } from '../router/router.reducer';

import { AbstractSdrRepo } from '../../model/sdr/repo/abstract-sdr-repo';

import { SdrResource, SdrCollection, SdrFacet, SdrFacetEntry, Count } from '../../model/sdr';
import { SidebarMenu, SidebarSection, SidebarItem, SidebarItemType } from '../../model/sidebar';
import { SolrDocument } from '../../model/discovery';
import { SdrRequest, Facetable, Indexable, Direction, Sort, Pageable } from '../../model/request';
import { OperationKey, Facet, DiscoveryView, DirectoryView, FacetSort } from '../../model/view';

import { injectable, repos } from '../../model/repos';

import { selectAllResources } from './';
import { selectRouterState } from '../router';
import { selectIsStompConnected, selectStompState } from '../stomp';

import * as fromDialog from '../dialog/dialog.actions';
import * as fromRouter from '../router/router.actions';
import * as fromStomp from '../stomp/stomp.actions';
import * as fromSdr from './sdr.actions';
import * as fromSidebar from '../sidebar/sidebar.actions';

@Injectable()
export class SdrEffects {

    private repos: Map<string, AbstractSdrRepo<SdrResource>>;

    constructor(
        private actions: Actions,
        private injector: Injector,
        private store: Store<AppState>,
        private alert: AlertService,
        private dialog: DialogService,
        private translate: TranslateService
    ) {
        this.repos = new Map<string, AbstractSdrRepo<SdrResource>>();
        this.injectRepos();
    }

    // TODO: alerts should be in dialog location if a dialog is opened

    @Effect() getAll = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ALL, ['directoryViews', 'discoveryViews', 'displayViews'])),
        switchMap((action: fromSdr.GetAllResourcesAction) => this.getAllHandler(action.name))
    );

    @Effect({ dispatch: false }) getAllSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ALL_SUCCESS, ['directoryViews', 'discoveryViews', 'displayViews'])),
        switchMap((action: fromSdr.GetAllResourcesSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() getAllFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ALL_FAILURE, ['directoryViews', 'discoveryViews', 'displayViews'])),
        map((action: fromSdr.GetAllResourcesFailureAction) => this.alert.getAllFailureAlert(action.payload))
    );

    @Effect() getOne = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ONE)),
        switchMap((action: fromSdr.GetOneResourceAction) => this.repos.get(action.name).getOne(action.payload.id).pipe(
            map((document: SolrDocument) => new fromSdr.GetOneResourceSuccessAction(action.name, { document })),
            catchError((response) => of(new fromSdr.GetOneResourceFailureAction(action.name, { response })))
        ))
    );

    @Effect({ dispatch: false }) getOneSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ONE_SUCCESS)),
        switchMap((action: fromSdr.GetOneResourceSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() getOneFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ONE_FAILURE)),
        map((action: fromSdr.GetOneResourceFailureAction) => this.alert.getOneFailureAlert(action.payload))
    );

    @Effect() findByIdIn = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.FIND_BY_ID_IN)),
        switchMap((action: fromSdr.FindByIdInResourceAction) => this.repos.get(action.name).findByIdIn(action.payload.ids).pipe(
            map((collection: SdrCollection) => new fromSdr.FindByIdInResourceSuccessAction(action.name, { collection })),
            catchError((response) => of(new fromSdr.FindByIdInResourceFailureAction(action.name, { response })))
        ))
    );

    @Effect({ dispatch: false }) findByIdInSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.FIND_BY_ID_IN_SUCCESS)),
        switchMap((action: fromSdr.FindByIdInResourceSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() gfindByIdInFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.FIND_BY_ID_IN_FAILURE)),
        map((action: fromSdr.FindByIdInResourceFailureAction) => this.alert.findByIdInFailureAlert(action.payload))
    );

    @Effect() findByTypesIn = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.FIND_BY_TYPES_IN)),
        switchMap((action: fromSdr.FindByTypesInResourceAction) => this.repos.get(action.name).findByTypesIn(action.payload.types).pipe(
            map((document: SolrDocument) => new fromSdr.FindByTypesInResourceSuccessAction(action.name, { document })),
            catchError((response) => of(new fromSdr.FindByTypesInResourceFailureAction(action.name, { response })))
        ))
    );

    @Effect({ dispatch: false }) findByTypesInSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.FIND_BY_TYPES_IN_SUCCESS)),
        switchMap((action: fromSdr.FindByTypesInResourceSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() gfindByTypesInFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.FIND_BY_TYPES_IN_FAILURE)),
        map((action: fromSdr.FindByTypesInResourceFailureAction) => this.alert.findByTypesInFailureAlert(action.payload))
    );

    @Effect() getDirectoryViews = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL, 'directoryViews')),
        switchMap((action: fromSdr.GetAllResourcesAction) => this.getAllHandler(action.name))
    );

    @Effect({ dispatch: false }) getDirectoryViewsSuccess = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL_SUCCESS, 'directoryViews')),
        switchMap((action: fromSdr.GetAllResourcesSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() getDirectoryViewsFailure = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL_FAILURE, 'directoryViews')),
        map((action: fromSdr.GetAllResourcesFailureAction) => this.alert.getAllFailureAlert(action.payload))
    );

    @Effect() getDiscoveryViews = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL, 'discoveryViews')),
        switchMap((action: fromSdr.GetAllResourcesAction) => this.getAllHandler(action.name))
    );

    @Effect({ dispatch: false }) getDiscoveryViewsSuccess = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL_SUCCESS, 'discoveryViews')),
        switchMap((action: fromSdr.GetAllResourcesSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() getDiscoveryViewsFailure = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL_FAILURE, 'discoveryViews')),
        map((action: fromSdr.GetAllResourcesFailureAction) => this.alert.getAllFailureAlert(action.payload))
    );

    @Effect() getDisplayViews = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL, 'displayViews')),
        switchMap((action: fromSdr.GetAllResourcesAction) => this.getAllHandler(action.name))
    );

    @Effect({ dispatch: false }) getDisplayViewsSuccess = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL_SUCCESS, 'displayViews')),
        switchMap((action: fromSdr.GetAllResourcesSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() getDisplayViewsFailure = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.GET_ALL_FAILURE, 'displayViews')),
        map((action: fromSdr.GetAllResourcesFailureAction) => this.alert.getAllFailureAlert(action.payload))
    );

    @Effect() page = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE)),
        switchMap((action: fromSdr.PageResourcesAction) =>
            this.repos.get(action.name).page(action.payload.request).pipe(
                map((collection: SdrCollection) => new fromSdr.PageResourcesSuccessAction(action.name, { collection })),
                catchError((response) => of(new fromSdr.PageResourcesFailureAction(action.name, { response })))
            )
        )
    );

    @Effect({ dispatch: false }) pageSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE_SUCCESS)),
        switchMap((action: fromSdr.PageResourcesSuccessAction) => this.waitForStompConnection(action.name)),
        withLatestFrom(this.store.pipe(select(selectStompState))),
        map(([combination, stomp]) => this.subscribeToResourceQueue(combination[0], stomp))
    );

    @Effect() pageFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE_FAILURE)),
        map((action: fromSdr.PageResourcesFailureAction) => this.alert.pageFailureAlert(action.payload))
    );

    @Effect() search = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.SEARCH)),
        switchMap((action: fromSdr.SearchResourcesAction) =>
            this.repos.get(action.name).search(action.payload.request).pipe(
                map((collection: SdrCollection) => new fromSdr.SearchResourcesSuccessAction(action.name, { collection })),
                catchError((response) => of(new fromSdr.SearchResourcesFailureAction(action.name, { response })))
            )
        )
    );

    @Effect({ dispatch: false }) searchSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.SEARCH_SUCCESS)),
        switchMap((action: fromSdr.SearchResourcesSuccessAction) => combineLatest(
            of(action),
            this.store.pipe(select(selectRouterState)),
            this.store.pipe(
                select(selectAllResources('directoryViews')),
                filter((views: DirectoryView[]) => views.length !== 0)
            ),
            this.store.pipe(
                select(selectAllResources('discoveryViews')),
                filter((views: DiscoveryView[]) => views.length !== 0)
            ),
            this.store.pipe(
                select(selectIsStompConnected),
                skipWhile((connected: boolean) => !connected),
                take(1)
            )
        )),
        withLatestFrom(this.store),
        map(([combination, store]) => this.searchSuccessHandler(combination[0], combination[1].state, store))
    );

    @Effect() searchFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.SEARCH_FAILURE)),
        map((action: fromSdr.SearchResourcesFailureAction) => this.alert.searchFailureAlert(action.payload))
    );

    @Effect() conceptsCount = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.COUNT, 'concepts')),
        switchMap((action: fromSdr.CountResourcesAction) => this.countHandler(action))
    );

    @Effect() documentsCount = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.COUNT, 'documents')),
        switchMap((action: fromSdr.CountResourcesAction) => this.countHandler(action))
    );

    @Effect() organizationsCount = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.COUNT, 'organizations')),
        switchMap((action: fromSdr.CountResourcesAction) => this.countHandler(action))
    );

    @Effect() personsCount = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.COUNT, 'persons')),
        switchMap((action: fromSdr.CountResourcesAction) => this.countHandler(action))
    );

    @Effect() processesCount = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.COUNT, 'processes')),
        switchMap((action: fromSdr.CountResourcesAction) => this.countHandler(action))
    );

    @Effect() relationshipsCount = this.actions.pipe(
        ofType(fromSdr.getSdrAction(fromSdr.SdrActionTypes.COUNT, 'relationships')),
        switchMap((action: fromSdr.CountResourcesAction) => this.countHandler(action))
    );

    @Effect() countFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.COUNT_FAILURE)),
        map((action: fromSdr.CountResourcesFailureAction) => this.alert.countFailureAlert(action.payload))
    );

    @Effect() clearResourceSubscription = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.CLEAR)),
        map((action: fromSdr.PageResourcesSuccessAction) => new fromStomp.UnsubscribeAction({ channel: `/queue/${action.name}` }))
    );

    @Effect() post = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST)),
        switchMap((action: fromSdr.PostResourceAction) =>
            this.repos.get(action.name).post(action.payload.resource).pipe(
                map((resource: SdrResource) => new fromSdr.PostResourceSuccessAction(action.name, { resource })),
                catchError((response) => of(new fromSdr.PostResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() postSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST_SUCCESS)),
        switchMap((action: fromSdr.PostResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.postSuccessAlert(action)
        ])
    );

    @Effect() postFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST_FAILURE)),
        map((action: fromSdr.PostResourceFailureAction) => this.alert.postFailureAlert(action.payload))
    );

    @Effect() put = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT)),
        switchMap((action: fromSdr.PutResourceAction) =>
            this.repos.get(action.name).put(action.payload.resource).pipe(
                map((resource: SdrResource) => new fromSdr.PutResourceSuccessAction(action.name, { resource })),
                catchError((response) => of(new fromSdr.PutResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() putSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT_SUCCESS)),
        switchMap((action: fromSdr.PutResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.putSuccessAlert(action)
        ])
    );

    @Effect() putFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT_FAILURE)),
        map((action: fromSdr.PutResourceFailureAction) => this.alert.putFailureAlert(action.payload))
    );

    @Effect() patch = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH)),
        switchMap((action: fromSdr.PatchResourceAction) =>
            this.repos.get(action.name).patch(action.payload.resource).pipe(
                map((resource: SdrResource) => new fromSdr.PatchResourceSuccessAction(action.name, { resource })),
                catchError((response) => of(new fromSdr.PatchResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() patchSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH_SUCCESS)),
        switchMap((action: fromSdr.PatchResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.patchSuccessAlert(action)
        ])
    );

    @Effect() patchFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH_FAILURE)),
        map((action: fromSdr.PatchResourceFailureAction) => this.alert.patchFailureAlert(action.payload))
    );

    @Effect() delete = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE)),
        switchMap((action: fromSdr.DeleteResourceAction) =>
            this.repos.get(action.name).delete(action.payload.id).pipe(
                map(() => new fromSdr.DeleteResourceSuccessAction(action.name)),
                catchError((response) => of(new fromSdr.DeleteResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() deleteSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE_SUCCESS)),
        switchMap((action: fromSdr.DeleteResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.deleteSuccessAlert(action)
        ])
    );

    @Effect() deleteFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE_FAILURE)),
        map((action: fromSdr.DeleteResourceFailureAction) => this.alert.deleteFailureAlert(action.payload))
    );

    @Effect({ dispatch: false }) navigation = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.CHANGED),
        withLatestFrom(this.store.pipe(select(selectRouterState))),
        map(([action, router]) => {
            let collection = router.state.data.collection;
            if (collection === undefined) {
                collection = router.state.queryParams.collection;
            }
            if (collection) {
                const request = this.createSdrRequest(router.state);
                if (router.state.url.startsWith('/directory') || router.state.url.startsWith('/discovery')) {
                    this.store.dispatch(new fromSdr.SearchResourcesAction(collection, { request }));
                } else {
                    this.store.dispatch(new fromSdr.PageResourcesAction(collection, { request }));
                }
            }
        })
    );

    @Effect() initViews = defer(() => of(
        new fromSdr.GetAllResourcesAction('directoryViews'),
        new fromSdr.GetAllResourcesAction('discoveryViews')
    ));

    private injectRepos(): void {
        const injector = Injector.create({
            providers: injectable,
            parent: this.injector
        });
        for (const name in repos) {
            if (repos.hasOwnProperty(name)) {
                this.repos.set(name, injector.get<AbstractSdrRepo<SdrResource>>(repos[name]));
            }
        }
    }

    private buildActions(actionType: fromSdr.SdrActionTypes, exclude: string[] = []): string[] {
        const loadActions = [];
        for (const name in repos) {
            if (repos.hasOwnProperty(name) && !exclude.includes(name)) {
                loadActions.push(fromSdr.getSdrAction(actionType, name));
            }
        }
        return loadActions;
    }

    private waitForStompConnection(name: string): Observable<[string, boolean]> {
        return combineLatest(
            of(name),
            this.store.pipe(
                select(selectIsStompConnected),
                skipWhile((connected: boolean) => !connected),
                take(1)
            )
        );
    }

    private subscribeToResourceQueue(name: string, stomp: StompState): void {
        if (!stomp.subscriptions.has(`/queue/${name}`)) {
            this.store.dispatch(new fromStomp.SubscribeAction({
                channel: `/queue/${name}`,
                handle: (frame: any) => {
                    // TODO: conditionally reload all
                    if (frame.command === 'MESSAGE') {
                        console.log(frame);
                    }
                }
            }));
        }
    }

    private getAllHandler(name: string): Observable<fromSdr.GetAllResourcesSuccessAction | fromSdr.GetAllResourcesFailureAction> {
        return this.repos.get(name).getAll().pipe(
            map((collection: SdrCollection) => new fromSdr.GetAllResourcesSuccessAction(name, { collection })),
            catchError((response) => of(new fromSdr.GetAllResourcesFailureAction(name, { response })))
        );
    }

    private countHandler(action: fromSdr.CountResourcesAction): Observable<fromSdr.CountResourcesSuccessAction | fromSdr.CountResourcesFailureAction> {
        return this.repos.get(action.name).count(action.payload.request).pipe(
            map((count: Count) => new fromSdr.CountResourcesSuccessAction(action.name, { count })),
            catchError((response) => of(new fromSdr.CountResourcesFailureAction(action.name, { response })))
        );
    }

    private searchSuccessHandler(action: fromSdr.SearchResourcesSuccessAction, routerState: CustomRouterState, store: AppState): void {
        if (routerState.queryParams.collection) {

            let facets: Facet[] = [];

            if (routerState.url.startsWith('/directory')) {
                facets = store['directoryViews'].entities[routerState.params.view].facets;
            } else if (routerState.url.startsWith('/discovery')) {
                facets = store['discoveryViews'].entities[routerState.params.view].facets;
            }

            const sdrFacets: SdrFacet[] = action.payload.collection.facets;

            const sidebarMenu: SidebarMenu = {
                sections: []
            };

            facets.filter((facet: Facet) => !facet.hidden).forEach((facet: Facet) => {
                for (const sdrFacet of sdrFacets) {
                    if (sdrFacet.field === facet.field) {

                        const sidebarSection: SidebarSection = {
                            title: of(facet.name),
                            items: [],
                            collapsible: true,
                            collapsed: facet.collapsed
                        };

                        Object.assign(sdrFacet, {
                            entries: sdrFacet.entries.sort(this.getFacetSortFunction(facet))
                        });

                        sdrFacet.entries.slice(0, facet.limit).forEach((facetEntry: SdrFacetEntry) => {
                            let selected = false;

                            for (const requestFacet of routerState.queryParams.facets.split(',')) {
                                if (routerState.queryParams[`${requestFacet}.filter`] === facetEntry.value) {
                                    selected = true;
                                    break;
                                }
                            }

                            const sidebarItem: SidebarItem = {
                                type: SidebarItemType.FACET,
                                label: of(facetEntry.value),
                                facet: facet,
                                selected: selected,
                                parenthetical: facetEntry.count,
                                route: [],
                                queryParams: {},
                            };

                            sidebarItem.queryParams[`${sdrFacet.field}.filter`] = !selected ? facetEntry.value : undefined;

                            sidebarItem.queryParams.page = 1;

                            if (selected) {
                                sidebarSection.collapsed = false;
                            }

                            sidebarSection.items.push(sidebarItem);
                        });

                        if (sdrFacet.entries.length > facet.limit) {
                            sidebarSection.items.push({
                                type: SidebarItemType.ACTION,
                                action: this.dialog.facetEntriesDialog(facet.name, sdrFacet),
                                label: this.translate.get('SHARED.SIDEBAR.ACTION.MORE')
                            });
                        }

                        sidebarMenu.sections.push(sidebarSection);
                        break;
                    }
                }
            });
            this.store.dispatch(new fromSidebar.LoadSidebarAction({ menu: sidebarMenu }));
        }
        this.subscribeToResourceQueue(action.name, store.stomp);
    }

    private getFacetSortFunction(facet: Facet): (f1: SdrFacetEntry, f2: SdrFacetEntry) => number {
        const sort = FacetSort.COUNT === FacetSort[facet.sort] ? 'count' : 'value';
        const direction = Direction.ASC === Direction[facet.direction] ? [1, -1] : [-1, 1];
        return (f1: SdrFacetEntry, f2: SdrFacetEntry) => {
            if (f1[sort] > f2[sort]) {
                return direction[0];
            }
            if (f1[sort] < f2[sort]) {
                return direction[1];
            }
            return 0;
        };
    }

    private createSdrRequest(routerState: CustomRouterState): SdrRequest {
        const queryParams = routerState.queryParams;
        return {
            pageable: this.buildPageable(queryParams),
            facets: this.buildFacets(queryParams),
            indexable: this.buildIndexable(queryParams),
            query: queryParams.query
        };
    }

    private buildPageable(queryParams: Params): Pageable {
        return {
            number: queryParams.page,
            size: queryParams.size,
            sort: this.buildSort(queryParams.sort)
        };
    }

    private buildSort(sortParams: string): Sort[] {
        const sort: Sort[] = [];
        if (sortParams !== undefined) {
            if (Array.isArray(sortParams)) {
                sortParams.forEach((currentSortParam) => sort.push(this.splitSort(currentSortParam)));
            } else {
                sort.push(this.splitSort(sortParams));
            }
        }
        return sort;
    }

    private splitSort(sortParam: string): Sort {
        const sortSplit = sortParam.split(',');
        return {
            name: sortSplit[0],
            direction: Direction[sortSplit[1] !== undefined ? sortSplit[1].toUpperCase() : 'ASC']
        };
    }

    private buildFacets(queryParams: Params): Facetable[] {
        const facets: Facetable[] = [];
        const fields: string[] = queryParams.facets !== undefined ? queryParams.facets.split(',') : [];
        fields.forEach((field: string) => {
            const facet: Facetable = { field };
            if (queryParams[`${field}.filter`]) {
                facet.filter = queryParams[`${field}.filter`];
            }
            facets.push(facet);
        });
        return facets;
    }

    private buildIndexable(queryParams: Params): Indexable {
        if (queryParams.index) {
            const indexSplit: string[] = queryParams.index.split(',');
            return {
                field: indexSplit[0],
                operationKey: OperationKey[indexSplit[1]],
                option: indexSplit[2]
            };
        }
    }

}
