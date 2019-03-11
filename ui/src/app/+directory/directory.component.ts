import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { SdrRequest } from '../core/model/request';
import { CollectionView, DirectoryView } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrFacet } from '../core/model/sdr';

import { selectAllResources, selectResourcesPage, selectResourcesFacets, selectResourceById } from '../core/store/sdr';

import * as fromSdr from '../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent implements OnDestroy, OnInit {

    public directoryView: Observable<CollectionView>;

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
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.name) {
                this.directoryView = this.store.pipe(
                    select(selectResourceById('directoryViews', params.name)),
                    filter((directoryView: CollectionView) => directoryView !== undefined)
                );
                this.subscriptions.push(this.directoryView.subscribe((directoryView: CollectionView) => {
                    this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(directoryView.collection)));
                    this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(directoryView.collection)));
                    this.facets = this.store.pipe(select(selectResourcesFacets<SolrDocument>(directoryView.collection)));
                }));
            }
        }));
    }

    public getRouterLink(directoryView: DirectoryView): string[] {
        return ['/directory', directoryView.name];
    }

    public getQueryParams(directoryView: DirectoryView, option: string): Params {
        return {
            index: `${directoryView.index.field},${directoryView.index.operationKey},${option}`,
            sort: `${directoryView.index.field},asc`
        };
    }

    public getResetQueryParams(directoryView: DirectoryView): Params {
        return {
            sort: `${directoryView.index.field},asc`
        };
    }

    public onPageChange(request: SdrRequest): void {
        this.store.dispatch(new fromSdr.SearchResourcesAction(request.collection, { request }));
    }

}
