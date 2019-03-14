import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { DirectoryView, Facet, Filter, DiscoveryView } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrFacet } from '../core/model/sdr';

import { selectAllResources, selectResourcesPage, selectResourcesFacets, selectResourceById, selectDefaultDiscoveryView } from '../core/store/sdr';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent implements OnDestroy, OnInit {

    public directoryView: Observable<DirectoryView>;

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
        this.discoveryView = this.store.pipe(
            select(selectDefaultDiscoveryView),
            filter((view: DiscoveryView) => view !== undefined)
        );
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.view) {
                this.directoryView = this.store.pipe(
                    select(selectResourceById('directoryViews', params.view)),
                    filter((view: DirectoryView) => view !== undefined)
                );
                this.subscriptions.push(this.directoryView.subscribe((directoryView: DirectoryView) => {
                    this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(directoryView.collection)));
                    this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(directoryView.collection)));
                    this.facets = this.store.pipe(select(selectResourcesFacets<SolrDocument>(directoryView.collection)));
                }));
            }
        }));

    }

    public getDirectoryRouterLink(directoryView: DirectoryView): string[] {
        return ['/directory', directoryView.name];
    }

    public getDirectoryQueryParams(directoryView: DirectoryView, option: string): Params {
        const queryParams: Params = this.getResetQueryParams(directoryView);
        queryParams.index = `${directoryView.index.field},${directoryView.index.operationKey},${option}`;
        return queryParams;
    }

    public getResetQueryParams(directoryView: DirectoryView): Params {
        const queryParams: Params = {};
        queryParams.collection = directoryView.collection;
        queryParams.sort = `${directoryView.index.field},asc`;
        queryParams.index = undefined;
        if (directoryView.facets && directoryView.facets.length > 0) {
            let facets = '';
            directoryView.facets.forEach((facet: Facet) => {
                facets += facets.length > 0 ? `,${facet.field}` : facet.field;
            });
            queryParams.facets = facets;
        }
        if (directoryView.filters && directoryView.filters.length > 0) {
            // tslint:disable-next-line:no-shadowed-variable
            directoryView.filters.forEach((filter: Filter) => {
                queryParams[`${filter.field}.filter`] = filter.value;
            });
        }
        queryParams.sort = `${directoryView.index.field},asc`;
        queryParams.page = 1;
        return queryParams;
    }

}
