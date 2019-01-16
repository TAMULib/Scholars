import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';

import { AppState } from '../core/store';

import * as fromSidebar from '../core/store/sidebar/sidebar.actions';

@Component({
    selector: 'scholars-admin',
    templateUrl: 'admin.component.html',
    styleUrls: ['admin.component.scss']
})
export class AdminComponent implements OnInit {

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.store.dispatch(new fromSidebar.LoadSidebarAction({
            menu: {
                sections: [
                    {
                        title: 'Administration',
                        items: [
                            {
                                label: 'Themes',
                                route: ['/admin/themes'],
                            },
                            {
                                label: 'Users',
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
