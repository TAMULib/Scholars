import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Store } from '@ngrx/store';

import { Subscription } from 'rxjs';

import { AppState } from '../core/store';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent implements OnDestroy, OnInit {

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
            if (params.collection) {
                console.log(params.collection);
            }
        }));
    }

}
