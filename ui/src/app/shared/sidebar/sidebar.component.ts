import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SidebarMenu } from '../../core/model/sidebar';

import { selectMenu } from '../../core/store/sidebar';

import * as fromSidebar from '../../core/store/sidebar/sidebar.actions';

@Component({
    selector: 'scholars-sidebar',
    templateUrl: 'sidebar.component.html',
    styleUrls: ['sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    public menu: Observable<SidebarMenu>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.menu = this.store.pipe(select(selectMenu));
    }

    public toggleSectionCollapse(sectionIndex: number): void {
        this.store.dispatch(new fromSidebar.ToggleCollapsibleSectionAction({ sectionIndex }));
    }

}
