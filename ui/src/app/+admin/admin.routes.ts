import { Routes } from '@angular/router';

import { AdminComponent } from './admin.component';
import { ThemesComponent } from './themes/themes.component';
import { UsersComponent } from './users/users.component';

import { DirectoryViewsComponent } from './directory-views/directory-views.component';
import { DiscoveryViewsComponent } from './discovery-views/discovery-views.component';
import { DisplayViewsComponent } from './display-views/display-views.component';

export const routes: Routes = [
    {
        path: '', component: AdminComponent, children: [
            {
                path: 'DirectoryViews', component: DirectoryViewsComponent, data: {
                    collection: 'directoryViews',
                    tags: [{ name: 'view', content: 'Scholars Administration - Directory Views' }]
                }
            },
            {
                path: 'DiscoveryViews', component: DiscoveryViewsComponent, data: {
                    collection: 'discoveryViews',
                    tags: [{ name: 'view', content: 'Scholars Administration - Discovery Views' }]
                }
            },
            {
                path: 'DisplayViews', component: DisplayViewsComponent, data: {
                    collection: 'displayViews',
                    tags: [{ name: 'view', content: 'Scholars Administration - Display Views' }]
                }
            },
            {
                path: 'Themes', component: ThemesComponent, data: {
                    collection: 'themes',
                    tags: [{ name: 'view', content: 'Scholars Administration - Themes' }]
                }
            },
            {
                path: 'Users', component: UsersComponent, data: {
                    collection: 'users',
                    tags: [{ name: 'view', content: 'Scholars Administration - Users' }]
                }
            },
            { path: '**', redirectTo: 'DirectoryViews' }
        ]
    }
];
