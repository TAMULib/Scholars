import { Component, OnDestroy, OnInit, OnChanges, AfterViewChecked } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { CollectionView } from '../core/model/view';
import { SolrDocument } from '../core/model/discovery';
import { SdrPage, SdrPageRequest } from '../core/model/sdr';

import { selectAllResources, selectResourcesPage, selectResourceById } from '../core/store/sdr';

import * as fromSdr from '../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent implements OnDestroy, OnInit {

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
        this.store.dispatch(new fromSdr.GetAllResourcesAction('directoryViews'));
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.name) {
                this.view = this.store.pipe(
                    select(selectResourceById('directoryViews', params.name)),
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
