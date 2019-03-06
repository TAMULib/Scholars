import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Store } from '@ngrx/store';

import { AppState } from '../core/store';

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
                        label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.TITLE'),
                        items: [
                            {
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.DIRECTORY_VIEWS'),
                                route: ['/admin/directroyViews'],
                            },
                            {
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.DISCOVERY_VIEWS'),
                                route: ['/admin/discoveryViews'],
                            },
                            {
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.RESULT_VIEWS'),
                                route: ['/admin/resultViews'],
                            },
                            {
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.THEMES'),
                                route: ['/admin/themes'],
                            },
                            {
                                label: this.translate.get('SHARED.SIDEBAR.ADMINISTRATION.USERS'),
                                route: ['/admin/users'],
                            }
                        ],
                        collapsible: false
                    }
                ],
                collapsible: true
            }
        }));
    }

}
