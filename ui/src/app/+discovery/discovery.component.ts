import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { DiscoveryView, Filter, Facet } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrFacet } from '../core/model/sdr';

import { selectRouterSearchQuery, selectRouterUrl, selectRouterQueryParamFilters } from '../core/store/router';
import { selectAllResources, selectResourcesPage, selectResourcesFacets, selectResourceById } from '../core/store/sdr';

@Component({
    selector: 'scholars-discovery',
    templateUrl: 'discovery.component.html',
    styleUrls: ['discovery.component.scss']
})
export class DiscoveryComponent implements OnDestroy, OnInit {

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
        this.url = this.store.pipe(select(selectRouterUrl));
        this.query = this.store.pipe(select(selectRouterSearchQuery));
        this.filters = this.store.pipe(select(selectRouterQueryParamFilters));
        this.discoveryViews = this.store.pipe(select(selectAllResources<DiscoveryView>('discoveryViews')));
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.view) {
                this.discoveryView = this.store.pipe(
                    select(selectResourceById('discoveryViews', params.view)),
                    filter((view: DiscoveryView) => view !== undefined)
                );
                this.subscriptions.push(this.discoveryView.subscribe((discoveryView: DiscoveryView) => {
                    this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(discoveryView.collection)));
                    this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(discoveryView.collection)));
                    this.facets = this.store.pipe(select(selectResourcesFacets<SolrDocument>(discoveryView.collection)));
                }));
            }
        }));
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

    public getDiscoveryRouteLink(discoveryView: DiscoveryView): string[] {
        return ['/discovery', discoveryView.name];
    }

    public getDiscoveryQueryParams(discoveryView: DiscoveryView, query: string, filters: Filter[] = [], removeFilter: Filter): Params {
        const queryParams: Params = {};
        queryParams.collection = discoveryView.collection;
        if (discoveryView.facets && discoveryView.facets.length > 0) {
            let facets = '';
            discoveryView.facets.forEach((facet: Facet) => {
                facets += facets.length > 0 ? `,${facet.field}` : facet.field;
            });
            queryParams.facets = facets;
        }
        if (discoveryView.filters && discoveryView.filters.length > 0) {
            // tslint:disable-next-line:no-shadowed-variable
            discoveryView.filters.forEach((filter: Filter) => {
                queryParams[`${filter.field}.filter`] = filter.value;
            });
        }
        // tslint:disable-next-line:no-shadowed-variable
        filters.filter((filter: Filter) => !this.equals(filter, removeFilter)).forEach((filter: Filter) => {
            queryParams[`${filter.field}.filter`] = filter.value;
        });
        if (query && query.length > 0) {
            queryParams.query = query;
        }
        return queryParams;
    }

    private equals(filterOne: Filter, filterTwo: Filter): boolean {
        return filterOne.field === filterTwo.field && filterOne.value === filterTwo.value;
    }

}
