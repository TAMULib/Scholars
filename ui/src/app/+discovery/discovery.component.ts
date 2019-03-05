import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';

import { AppState } from '../core/store';

import { selectRouterSearchQuery } from '../core/store/router';

@Component({
    selector: 'scholars-discovery',
    templateUrl: 'discovery.component.html',
    styleUrls: ['discovery.component.scss']
})
export class DiscoveryComponent implements OnDestroy, OnInit {

    public live = true;

    public query: Observable<string>;

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
        this.subscriptions.push(this.route.params.subscribe((params) => {
            if (params.collection) {
                console.log(params.collection);
            }
        }));
    }

}
