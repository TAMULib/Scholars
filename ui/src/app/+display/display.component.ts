import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';

import { DisplayView } from '../core/model/view';
import { WindowDimensions } from '../core/store/layout/layout.reducer';

import { selectWindowDimensions } from '../core/store/layout';
import { SolrDocument } from '../core/model/discovery';

import { selectResourceById } from '../core/store/sdr';

import * as fromSdr from '../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-display',
    templateUrl: 'display.component.html',
    styleUrls: ['display.component.scss']
})
export class DisplayComponent implements OnDestroy, OnInit {

    public windowDimensions: Observable<WindowDimensions>;

    public displayView: Observable<DisplayView>;

    public document: Observable<SolrDocument>;

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
        this.subscriptions.push(this.route.params.subscribe((params: Params) => {
            console.log(params);
            if (params.collection && params.id) {
                this.store.dispatch(new fromSdr.GetOneResourceAction(params.collection, { id: params.id }));
                this.document = this.store.pipe(
                    select(selectResourceById(params.collection, params.id)),
                    filter((document: SolrDocument) => document !== undefined)
                );
                this.document.subscribe((document: SolrDocument) => {
                    console.log(document);
                });
            }
        }));
    }

    public showTabs(windowDimensions: WindowDimensions): boolean {
        return windowDimensions.width > 767;
    }

}
