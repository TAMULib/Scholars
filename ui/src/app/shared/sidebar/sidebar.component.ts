import { Component, OnInit, Input } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { selectIsSidebarCollapsed } from '../../core/store/layout';

import * as fromLayout from '../../core/store/layout/layout.actions';

export interface SidebarItem {
    label: string;
    route: string[];
    additionalClass?: string;
}

export interface SidebarSection {
    title: string;
    items: SidebarItem[];
    additionalClass?: string;
    collapsible: boolean;
}

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsible: boolean;
    additionalClass?: string;
}

@Component({
    selector: 'scholars-sidebar',
    templateUrl: 'sidebar.component.html',
    styleUrls: ['sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    @Input() menu: SidebarMenu;

    public isSidebarCollapsed: Observable<boolean>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.isSidebarCollapsed = this.store.pipe(select(selectIsSidebarCollapsed));
    }

    public toggleSidebar(): void {
        this.store.dispatch(new fromLayout.ToggleSidebarAction());
    }

}
