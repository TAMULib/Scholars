import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from '../../core/store';
import { Navbar } from '../../core/model/theme/navbar';

import { selectIsNavbarCollapsed, selectIsNavbarExpanded } from '../../core/store/layout';
import { selectActiveThemeHeaderNavbar } from '../../core/store/theme';

import * as fromLayout from '../../core/store/layout/layout.actions';

@Component({
    selector: 'scholars-navbar',
    templateUrl: 'navbar.component.html',
    styleUrls: ['navbar.component.scss']
})
export class NavbarComponent implements OnInit {

    public isNavbarCollapsed: Observable<boolean>;

    public isNavbarExpanded: Observable<boolean>;

    public navbar: Observable<Navbar>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.isNavbarCollapsed = this.store.pipe(select(selectIsNavbarCollapsed));
        this.isNavbarExpanded = this.store.pipe(select(selectIsNavbarExpanded));
        this.navbar = this.store.pipe(
            select(selectActiveThemeHeaderNavbar),
            skipWhile((navbar: Navbar) => navbar === undefined)
        );
    }

    public toggleNavbar(): void {
        this.store.dispatch(new fromLayout.ToggleNavbarAction());
    }

}
