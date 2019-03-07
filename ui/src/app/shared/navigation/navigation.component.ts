import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { DirectoryView } from '../../core/model/view';

import { selectIsNavigationCollapsed } from '../../core/store/layout';

import { selectAllResources } from '../../core/store/sdr';

import * as fromLayout from '../../core/store/layout/layout.actions';
import * as fromSdr from '../../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-navigation',
    templateUrl: 'navigation.component.html',
    styleUrls: ['navigation.component.scss']
})
export class NavigationComponent implements OnInit {

    public directoryViews: Observable<DirectoryView[]>;

    public isNavigationCollapsed: Observable<boolean>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.directoryViews = this.store.pipe(select(selectAllResources<DirectoryView>('directoryViews')));
        this.isNavigationCollapsed = this.store.pipe(select(selectIsNavigationCollapsed));
        this.store.dispatch(new fromSdr.GetAllResourcesAction('directoryViews'));
    }

    public getDirectoryRoute(directoryView: DirectoryView): string[] {
        return [`/directory/${directoryView.name}`];
    }

    public toggleNavigation(): void {
        this.store.dispatch(new fromLayout.ToggleNavigationAction());
    }

}
