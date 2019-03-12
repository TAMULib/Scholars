import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription, of } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { SdrRequest } from '../core/model/request';
import { DiscoveryView, Filter, Facet } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrFacet, SdrFacetEntry } from '../core/model/sdr';
import { SidebarMenu, SidebarSection, SidebarItem } from '../core/model/sidebar';

import { selectRouterSearchQuery, selectRouterUrl } from '../core/store/router';
import { selectAllResources, selectResourcesPage, selectResourcesFacets, selectResourceById } from '../core/store/sdr';

import * as fromSdr from '../core/store/sdr/sdr.actions';
import * as fromSidebar from '../core/store/sidebar/sidebar.actions';

@Component({
    selector: 'scholars-discovery',
    templateUrl: 'discovery.component.html',
    styleUrls: ['discovery.component.scss']
})
export class DiscoveryComponent implements OnDestroy, OnInit {

    public url: Observable<string>;

    public query: Observable<string>;

    public discoveryViews: Observable<DiscoveryView[]>;

    public discoveryView: Observable<DiscoveryView>;

    public documents: Observable<SolrDocument[]>;

    public page: Observable<SdrPage>;

    public facets: Observable<SdrFacet[]>;

    private subscriptions: Subscription[];

    private request: SdrRequest;

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
        this.discoveryViews = this.store.pipe(select(selectAllResources<DiscoveryView>('discoveryViews')));
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.name) {
                this.discoveryView = this.store.pipe(
                    select(selectResourceById('discoveryViews', params.name)),
                    filter((discoveryView: DiscoveryView) => discoveryView !== undefined)
                );
                this.subscriptions.push(this.discoveryView.subscribe((discoveryView: DiscoveryView) => {
                    this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(discoveryView.collection)));
                    this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(discoveryView.collection)));
                    this.facets = this.store.pipe(
                        select(selectResourcesFacets<SolrDocument>(discoveryView.collection)),
                        filter((sdrFacets: SdrFacet[]) => sdrFacets.length > 0)
                    );

                    this.subscriptions.push(this.facets.subscribe((sdrFacets: SdrFacet[]) => {

                        const sidebarMenu: SidebarMenu = {
                            sections: [],
                            collapsible: { allowed: true }
                        };

                        discoveryView.facets.filter((facet: Facet) => !facet.hidden).forEach((facet: Facet) => {

                            for (const sdrFacet of sdrFacets) {

                                if (sdrFacet.field === facet.field) {

                                    const sidebarSection: SidebarSection = {
                                        title: of(facet.name),
                                        items: [],
                                        collapsible: { allowed: true }
                                    };

                                    sdrFacet.entries.forEach((facetEntry: SdrFacetEntry) => {

                                        let checked = false;

                                        for (const requestFacet of this.request.facets) {
                                            if (requestFacet.filter === facetEntry.value) {
                                                checked = true;
                                                break;
                                            }
                                        }

                                        const sidebarItem: SidebarItem = {
                                            label: of(facetEntry.value),
                                            total: facetEntry.count,
                                            route: [],
                                            checkbox: {
                                                id: facet.field,
                                                name: facet.name,
                                                type: 'checkbox',
                                                value: checked
                                            },
                                            queryParams: {}
                                        };

                                        sidebarItem.queryParams[`${sdrFacet.field}.filter`] = !checked ? facetEntry.value : undefined;

                                        sidebarSection.items.push(sidebarItem);

                                    });

                                    sidebarMenu.sections.push(sidebarSection);

                                    break;
                                }

                            }

                        });

                        if (sidebarMenu.sections.length > 0) {
                            this.store.dispatch(new fromSidebar.LoadSidebarAction({ menu: sidebarMenu }));
                        }

                    }));

                }));
            }
        }));
    }

    public isActive(discoveryView: DiscoveryView, url: string): boolean {
        return url.startsWith(`/discovery/${discoveryView.name}`);
    }

    public getDiscoveryRouteLink(discoveryView: DiscoveryView): string[] {
        return ['/discovery', discoveryView.name];
    }

    public getDiscoveryQueryParams(discoveryView: DiscoveryView, query: string): Params {
        const queryParams: Params = {};
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
        if (query && query.length > 0) {
            queryParams.query = query;
        }
        return queryParams;
    }

    public onPageChange(request: SdrRequest): void {
        this.request = request;
        this.store.dispatch(new fromSdr.SearchResourcesAction(request.collection, { request }));
    }

}
