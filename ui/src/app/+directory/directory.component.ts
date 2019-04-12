import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter, tap } from 'rxjs/operators';

import { AppState } from '../core/store';

import { DirectoryView, DiscoveryView } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrFacet } from '../core/model/sdr';

import { selectAllResources, selectResourcesPage, selectResourcesFacets, selectResourceById, selectDefaultDiscoveryView } from '../core/store/sdr';
import { selectRouterQueryParams } from '../core/store/router';

import { addFacetsToQueryParams, addFiltersToQueryParams } from '../shared/utilities/view.utility';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent implements OnDestroy, OnInit {

    public queryParams: Observable<Params>;

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
        this.queryParams = this.store.pipe(select(selectRouterQueryParams));
        this.discoveryView = this.store.pipe(
            select(selectDefaultDiscoveryView),
            filter((view: DiscoveryView) => view !== undefined)
        );
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.view) {
                this.directoryView = this.store.pipe(
                    select(selectResourceById('directoryViews', params.view)),
                    filter((view: DirectoryView) => view !== undefined),
                    tap((view: DirectoryView) => {
                        this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(view.collection)));
                        this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(view.collection)));
                        this.facets = this.store.pipe(select(selectResourcesFacets<SolrDocument>(view.collection)));
                    })
                );
            }
        }));
    }

    public isActive(queryParams: Params, option: string): boolean {
        if (queryParams.index !== undefined) {
            return queryParams.index.split(',')[2] === option;
        }
        return option === 'All';
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
        queryParams.index = undefined;
        queryParams.page = 1;
        addFacetsToQueryParams(queryParams, directoryView);
        addFiltersToQueryParams(queryParams, directoryView);
        // NOTE: currently ignoring sort of CollectionView and applying sort asc by index field
        queryParams.sort = `${directoryView.index.field},asc`;
        return queryParams;
    }

}
