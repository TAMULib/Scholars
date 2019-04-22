import { Component, Input, OnInit } from '@angular/core';
import { Params } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { DirectoryView } from '../../core/model/view';

import * as fromSdr from '../../core/store/sdr/sdr.actions';

import { selectResourcesCount, selectDirectoryViewByCollection } from '../../core/store/sdr';

import { addFacetsToQueryParams, addFiltersToQueryParams } from '../utilities/view.utility';

@Component({
    selector: 'scholars-stats-box',
    templateUrl: 'stats-box.component.html',
    styleUrls: ['stats-box.component.scss']
})
export class StatsBoxComponent implements OnInit {

    @Input() label: string;

    @Input() collection: string;

    public count: Observable<number>;

    public directoryView: Observable<DirectoryView>;

    constructor(private store: Store<AppState>) {

    }

    public ngOnInit() {
        this.directoryView = this.store.pipe(select(selectDirectoryViewByCollection(this.collection)));
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

    public getRouterLink(directoryView: DirectoryView): string[] {
        return [`/directory/${directoryView.name}`];
    }

    public getQueryParams(directoryView: DirectoryView): Params {
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
