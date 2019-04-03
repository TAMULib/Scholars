import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Store } from '@ngrx/store';

import { AppState } from '../core/store';
import { SidebarItemType } from '../core/model/sidebar';

import * as fromSidebar from '../core/store/sidebar/sidebar.actions';

@Component({
    selector: 'scholars-admin',
    templateUrl: 'admin.component.html',
    styleUrls: ['admin.component.scss']
})
export class AdminComponent implements OnInit {

    constructor(
        private store: Store<AppState>,
        private translate: TranslateService
    ) {

    }

    ngOnInit() {
        this.store.dispatch(new fromSidebar.LoadSidebarAction({
            menu: {
                sections: [
                    {
                        title: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.TITLE'),
                        items: [
                            {
                                type: SidebarItemType.LINK,
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.DIRECTORY_VIEWS'),
                                route: ['/admin/DirectoryViews']
                            },
                            {
                                type: SidebarItemType.LINK,
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.DISCOVERY_VIEWS'),
                                route: ['/admin/DiscoveryViews']
                            },
                            {
                                type: SidebarItemType.LINK,
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.DISPLAY_VIEWS'),
                                route: ['/admin/DisplayViews']
                            },
                            {
                                type: SidebarItemType.LINK,
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.THEMES'),
                                route: ['/admin/Themes']
                            },
                            {
                                type: SidebarItemType.LINK,
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.USERS'),
                                route: ['/admin/Users']
                            }
                        ],
                        collapsible: false,
                        collapsed: false
                    }
                ]
            }
        }));
    }

}
