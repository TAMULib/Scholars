import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { TranslateService } from '@ngx-translate/core';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SidebarMenu } from '../../core/model/sidebar';
import { Collapsable } from '../../core/model/theme/collapsable';

import { selectIsSidebarCollapsed } from '../../core/store/layout';
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

    public getSectionCollapsableIcon(collapsable: Collapsable): string {
        if (this.isSectionCollapsable(collapsable)) {
            return this.isSectionCollapsed(collapsable) ? 'fa-caret-right' : 'fa-caret-down';
        }

        return '';
    }

    public toggleSectionCollapse(sectionIndex: number): void {
        this.store.dispatch(new fromSidebar.ToggleCollapsableSectionAction({sectionIndex}));
    }

    public translateSectionCollapsableButton(collapsable: Collapsable, label: string): Observable<string> {
        let key = 'SHARED.SIDEBAR.SECTION.ARIA_LABEL_COLLAPSE';

        if (this.isSectionCollapsed(collapsable)) {
            key = 'SHARED.SIDEBAR.SECTION.ARIA_LABEL_EXPAND';
        }

        return this.translate.get(key, { label });
    }

    public isSectionCollapsable(collapsable: Collapsable): boolean {
        return collapsable.allowed;
    }

    public isSectionCollapsed(collapsable: Collapsable): boolean {
        return collapsable.allowed && collapsable.collapsed;
    }

}
