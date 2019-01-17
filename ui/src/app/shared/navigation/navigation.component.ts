import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { selectIsNavigationCollapsed } from '../../core/store/layout';

import * as fromLayout from '../../core/store/layout/layout.actions';

@Component({
    selector: 'scholars-navigation',
    templateUrl: 'navigation.component.html',
    styleUrls: ['navigation.component.scss']
})
export class NavigationComponent implements OnInit {

    public isNavigationCollapsed: Observable<boolean>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.isNavigationCollapsed = this.store.pipe(select(selectIsNavigationCollapsed));
    }

    public toggleNavigation(): void {
        this.store.dispatch(new fromLayout.ToggleNavigationAction());
    }

}