import { Routes } from '@angular/router';

import { AdminComponent } from './admin.component';
import { ThemesComponent } from './themes/themes.component';
import { UsersComponent } from './users/users.component';

import { DirectoryViewsComponent } from './directory-views/directory-views.component';
import { DiscoveryViewsComponent } from './discovery-views/discovery-views.component';

export const routes: Routes = [
    {
        path: '', component: AdminComponent, children: [
            {
                path: 'directroyViews', component: DirectoryViewsComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Administration - Directory Views' }]
                }
            },
            {
                path: 'discoveryViews', component: DiscoveryViewsComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Administration - Discovery Views' }]
                }
            },
            {
                path: 'themes', component: ThemesComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Administration - Themes' }]
                }
            },
            {
                path: 'users', component: UsersComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Administration - Users' }]
                }
            },
            { path: '**', redirectTo: 'directroyViews' }
        ]
    }
];
