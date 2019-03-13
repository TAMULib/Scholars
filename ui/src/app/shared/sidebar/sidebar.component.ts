import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { TranslateService } from '@ngx-translate/core';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SidebarMenu } from '../../core/model/sidebar';
import { Collapsible } from '../../core/model/theme/collapsible';

import { selectMenu } from '../../core/store/sidebar';

import * as fromSidebar from '../../core/store/sidebar/sidebar.actions';

@Component({
    selector: 'scholars-sidebar',
    templateUrl: 'sidebar.component.html',
    styleUrls: ['sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    public menu: Observable<SidebarMenu>;

    constructor(private store: Store<AppState>, private translate: TranslateService) {

    }

    ngOnInit() {
        this.menu = this.store.pipe(select(selectMenu));
    }

    public toggleSectionCollapse(sectionIndex: number): void {
        this.store.dispatch(new fromSidebar.ToggleCollapsibleSectionAction({ sectionIndex }));
    }

    public translateSectionCollapsibleButton(collapsible: Collapsible, label: string): Observable<string> {
        let key = 'SHARED.SIDEBAR.SECTION.ARIA_LABEL_COLLAPSE';
        if (this.isSectionCollapsed(collapsible)) {
            key = 'SHARED.SIDEBAR.SECTION.ARIA_LABEL_EXPAND';
        }
        return this.translate.get(key, { label });
    }

    public isSectionCollapsible(collapsible: Collapsible): boolean {
        return collapsible.allowed;
    }

    public isSectionCollapsed(collapsible: Collapsible): boolean {
        return collapsible.allowed && collapsible.collapsed;
    }

}
