import { Component, OnInit } from '@angular/core';
import { Params } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { DirectoryView, Facet, Filter } from '../../core/model/view';

import { selectIsNavigationCollapsed } from '../../core/store/layout';

import { selectRouterUrl } from '../../core/store/router';
import { selectAllResources } from '../../core/store/sdr';

import * as fromLayout from '../../core/store/layout/layout.actions';

@Component({
    selector: 'scholars-navigation',
    templateUrl: 'navigation.component.html',
    styleUrls: ['navigation.component.scss']
})
export class NavigationComponent implements OnInit {

    public url: Observable<string>;

    public directoryViews: Observable<DirectoryView[]>;

    public isNavigationCollapsed: Observable<boolean>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.url = this.store.pipe(select(selectRouterUrl));
        this.directoryViews = this.store.pipe(select(selectAllResources<DirectoryView>('directoryViews')));
        this.isNavigationCollapsed = this.store.pipe(select(selectIsNavigationCollapsed));
    }

    public isActive(directoryView: DirectoryView, url: string): boolean {
        return url.startsWith(`/directory/${directoryView.name}`);
    }

    public getDirectoryRouterLink(directoryView: DirectoryView): string[] {
        return ['/directory', directoryView.name];
    }

    // NOTE: redundant with getResetQueryParams in DirectoryComponent
    public getDirectoryQueryParams(directoryView: DirectoryView): Params {
        const queryParams: Params = {};
        queryParams.collection = directoryView.collection;
        queryParams.sort = `${directoryView.index.field},asc`;
        queryParams.index = undefined;
        if (directoryView.facets && directoryView.facets.length > 0) {
            let facets = '';
            directoryView.facets.forEach((facet: Facet) => {
                facets += facets.length > 0 ? `,${facet.field}` : facet.field;
            });
            queryParams.facets = facets;
        }
        if (directoryView.filters && directoryView.filters.length > 0) {
            directoryView.filters.forEach((filter: Filter) => {
                queryParams[`${filter.field}.filter`] = filter.value;
            });
        }
        return queryParams;
    }

    public toggleNavigation(): void {
        this.store.dispatch(new fromLayout.ToggleNavigationAction());
    }

}
