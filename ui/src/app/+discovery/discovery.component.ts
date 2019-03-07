import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { CollectionView } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrPageRequest } from '../core/model/sdr';

import { selectRouterSearchQuery } from '../core/store/router';
import { selectAllResources, selectResourcesPage, selectResourceById } from '../core/store/sdr';

import * as fromSdr from '../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-discovery',
    templateUrl: 'discovery.component.html',
    styleUrls: ['discovery.component.scss']
})
export class DiscoveryComponent implements OnDestroy, OnInit {

    public query: Observable<string>;

    public view: Observable<CollectionView>;

    public documents: Observable<SolrDocument[]>;

    public page: Observable<SdrPage>;

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
        this.query = this.store.pipe(select(selectRouterSearchQuery));
        this.store.dispatch(new fromSdr.GetAllResourcesAction('discoveryViews'));
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.name) {
                this.view = this.store.pipe(
                    select(selectResourceById('discoveryViews', params.name)),
                    filter((view: CollectionView) => view !== undefined)
                );
                this.subscriptions.push(this.view.subscribe((view: CollectionView) => {
                    this.documents = this.store.pipe(select(selectAllResources<SolrDocument>(view.collection)));
                    this.page = this.store.pipe(select(selectResourcesPage<SolrDocument>(view.collection)));
                }));
            }
        }));
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromSdr.PageResourcesAction(page.collection, { page }));
    }

}
