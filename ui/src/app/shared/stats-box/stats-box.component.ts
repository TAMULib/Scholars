import { Component, Input, OnInit } from '@angular/core';
import { Params } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { DirectoryView, Facet, Filter } from '../../core/model/view';

import * as fromSdr from '../../core/store/sdr/sdr.actions';

import { selectResourcesCount, selectDirectoryViewByCollection } from '../../core/store/sdr';

@Component({
    selector: 'scholars-stats-box',
    templateUrl: 'stats-box.component.html',
    styleUrls: ['stats-box.component.scss']
})
export class StatsBoxComponent implements OnInit {

    @Input() label: string;

    @Input() collection: string;

    public count: Observable<number>;

    public direcrotyView: Observable<DirectoryView>;

    constructor(private store: Store<AppState>) {

    }

    public ngOnInit() {
        this.direcrotyView = this.store.pipe(select(selectDirectoryViewByCollection(this.collection)));
        this.store.dispatch(new fromSdr.CountResourcesAction(this.collection, {
            request: {}
        }));
        this.count = this.store.pipe(select(selectResourcesCount(this.collection)));
    }

    public format(count: number): string | number {
        if (count >= 1.0e+9) {
            return (Math.abs(count) / 1.0e+9).toFixed(1).replace('.0', '') + 'm';
        } else if (count >= 1.0e+6) {
            return (Math.abs(count) / 1.0e+6).toFixed(1).replace('.0', '') + 'b';
        } else if (count >= 1.0e+3) {
            return (Math.abs(count) / 1.0e+3).toFixed(1).replace('.0', '') + 'k';
        } else {
            return count;
        }
    }

    public getRouterLink(direcrotyView: DirectoryView): string[] {
        return [`/directory/${direcrotyView.name}`];
    }

    // NOTE: redundant with getResetQueryParams in DirectoryComponent
    public getQueryParams(directoryView: DirectoryView): Params {
        const queryParams: Params = {};
        queryParams.collection = directoryView.collection;
        queryParams.index = undefined;
        queryParams.page = 1;
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
        // NOTE: currently ignoring sort of CollectionView and applying sort asc by index field
        queryParams.sort = `${directoryView.index.field},asc`;
        return queryParams;
    }

}
