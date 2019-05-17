import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../core/store';
import { DiscoveryView } from '../core/model/view';

import { WindowDimensions } from '../core/store/layout/layout.reducer';

import { selectWindowDimensions } from '../core/store/layout';
import { selectDefaultDiscoveryView } from '../core/store/sdr';

export interface AdminTab {
    route: string[];
    translateKey: string;
}

@Component({
    selector: 'scholars-admin',
    templateUrl: 'admin.component.html',
    styleUrls: ['admin.component.scss']
})
export class AdminComponent implements OnInit {

    public discoveryView: Observable<DiscoveryView>;

    public windowDimensions: Observable<WindowDimensions>;

    public tabs: AdminTab[];

    constructor(private store: Store<AppState>) {
        this.tabs = [
            {
                route: ['/admin/DirectoryViews'],
                translateKey: 'SHARED.SIDEBAR.ADMINISTRATION.DIRECTORY_VIEWS'
            },
            {
                route: ['/admin/DiscoveryViews'],
                translateKey: 'SHARED.SIDEBAR.ADMINISTRATION.DISCOVERY_VIEWS'
            },
            {
                route: ['/admin/DisplayViews'],
                translateKey: 'SHARED.SIDEBAR.ADMINISTRATION.DISPLAY_VIEWS'
            },
            {
                route: ['/admin/Themes'],
                translateKey: 'SHARED.SIDEBAR.ADMINISTRATION.THEMES'
            },
            {
                route: ['/admin/Users'],
                translateKey: 'SHARED.SIDEBAR.ADMINISTRATION.USERS'
            }
        ];
    }

    ngOnInit() {
        this.discoveryView = this.store.pipe(
            select(selectDefaultDiscoveryView),
            filter((view: DiscoveryView) => view !== undefined)
        );
        this.windowDimensions = this.store.pipe(select(selectWindowDimensions));
    }

    public showTabs(windowDimensions: WindowDimensions): boolean {
        return windowDimensions.width > 767;
    }

}
