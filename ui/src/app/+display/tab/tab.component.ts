import { Component, OnInit, OnDestroy } from '@angular/core';
import { Params, ActivatedRoute } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { Observable, Subscription, combineLatest } from 'rxjs';

import { DisplayTabView, DisplayTabSectionView } from '../../core/model/view';
import { SolrDocument } from '../../core/model/discovery';

import { AppState } from '../../core/store';

import { selectResourceById, selectDisplayViewTab } from '../../core/store/sdr';
import { sectionsToShow } from '../display.component';

@Component({
    selector: 'scholars-tab',
    templateUrl: './tab.component.html',
    styleUrls: ['./tab.component.scss']
})
export class TabComponent implements OnDestroy, OnInit {

    public tab: Observable<DisplayTabView>;

    public document: Observable<SolrDocument>;

    private subscriptions: Subscription[];

    constructor(
        private store: Store<AppState>,
        private route: ActivatedRoute
    ) {
        this.subscriptions = [];
    }

    ngOnInit() {
        this.subscriptions.push(combineLatest([
            this.route.parent.params,
            this.route.params
        ]).subscribe((params: Params[]) => {
            if (params[0].collection && params[0].id && params[1].view && params[1].tab) {
                this.tab = this.store.pipe(select(selectDisplayViewTab(params[1].view, params[1].tab)));
                this.document = this.store.pipe(select(selectResourceById(params[0].collection, params[0].id)));
            }
        }));
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    public getSectionsToShow(sections: DisplayTabSectionView[], document: SolrDocument): DisplayTabSectionView[] {
        return sectionsToShow(sections, document);
    }

}
