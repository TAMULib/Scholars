import { Component } from '@angular/core';
import { SidebarMenu } from '../shared/sidebar/sidebar.component';

@Component({
    selector: 'scholars-admin',
    templateUrl: 'admin.component.html',
    styleUrls: ['admin.component.scss']
})
export class AdminComponent {

    public menu: SidebarMenu = {
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
    };

}
