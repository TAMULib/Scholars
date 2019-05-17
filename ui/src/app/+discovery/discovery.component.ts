import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter, tap } from 'rxjs/operators';

import { AppState } from '../core/store';

import { DiscoveryView, Filter } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrFacet } from '../core/model/sdr';
import { WindowDimensions } from '../core/store/layout/layout.reducer';

import { selectRouterSearchQuery, selectRouterUrl, selectRouterQueryParamFilters } from '../core/store/router';
import { selectAllResources, selectResourcesPage, selectResourcesFacets, selectResourceById } from '../core/store/sdr';
import { selectWindowDimensions } from '../core/store/layout';

import { addFacetsToQueryParams, addFiltersToQueryParams, addSortToQueryParams } from '../shared/utilities/view.utility';

@Component({
    selector: 'scholars-discovery',
    templateUrl: 'discovery.component.html',
    styleUrls: ['discovery.component.scss']
})
export class DiscoveryComponent implements OnDestroy, OnInit {

    public windowDimensions: Observable<WindowDimensions>;

    public url: Observable<string>;

    public query: Observable<string>;

    public filters: Observable<any[]>;

    public discoveryViews: Observable<DiscoveryView[]>;

    public discoveryView: Observable<DiscoveryView>;

    public documents: Observable<SolrDocument[]>;

    public page: Observable<SdrPage>;

    public facets: Observable<SdrFacet[]>;

    private subscriptions: Subscription[];

    constructor(
        private store: Store<AppState>,
        private route: ActivatedRoute
    ) {
        this.subscriptions = [];
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    ngOnInit() {
        this.windowDimensions = this.store.pipe(select(selectWindowDimensions));
        this.url = this.store.pipe(select(selectRouterUrl));
        this.query = this.store.pipe(select(selectRouterSearchQuery));
        this.filters = this.store.pipe(select(selectRouterQueryParamFilters));
        this.discoveryViews = this.store.pipe(select(selectAllResources<DiscoveryView>('discoveryViews')));
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.view) {
                this.discoveryView = this.store.pipe(
                    select(selectResourceById('discoveryViews', params.view)),
                    filter((view: DiscoveryView) => view !== undefined),
                    tap((view: DiscoveryView) => {
                        this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(view.collection)));
                        this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(view.collection)));
                        this.facets = this.store.pipe(select(selectResourcesFacets<SolrDocument>(view.collection)));
                    })
                );
            }
        }));
    }

    public showTabs(windowDimensions: WindowDimensions): boolean {
        return windowDimensions.width > 767;
    }

    public isActive(discoveryView: DiscoveryView, url: string): boolean {
        return url.startsWith(`/discovery/${discoveryView.name}`);
    }

    public showFilter(discoveryView: DiscoveryView, actualFilter: Filter): boolean {
        // tslint:disable-next-line:no-shadowed-variable
        for (const filter of discoveryView.filters) {
            if (this.equals(filter, actualFilter)) {
                return false;
            }
        }
        return true;
    }

    public getDiscoveryRouterLink(discoveryView: DiscoveryView): string[] {
        return ['/discovery', discoveryView.name];
    }

    public getDiscoveryQueryParams(discoveryView: DiscoveryView, page: SdrPage, query: string, filters: Filter[] = [], removeFilter: Filter): Params {
        const queryParams: Params = {};
        queryParams.collection = discoveryView.collection;
        addFacetsToQueryParams(queryParams, discoveryView);
        addFiltersToQueryParams(queryParams, discoveryView);
        // NOTE: only first sort is applied to query
        addSortToQueryParams(queryParams, discoveryView);
        // tslint:disable-next-line:no-shadowed-variable
        filters.filter((filter: Filter) => !this.equals(filter, removeFilter)).forEach((filter: Filter) => {
            queryParams[`${filter.field}.filter`] = filter.value;
        });
        if (query && query.length > 0) {
            queryParams.query = query;
        }
        if (page && page.size) {
            queryParams.size = page.size;
        }
        return queryParams;
    }

    private equals(filterOne: Filter, filterTwo: Filter): boolean {
        return filterOne.field === filterTwo.field && filterOne.value === filterTwo.value;
    }

}
