import { Routes } from '@angular/router';

import { AdminComponent } from './admin.component';
import { ThemesComponent } from './themes/themes.component';
import { UsersComponent } from './users/users.component';

export const routes: Routes = [
    {
        path: '', component: AdminComponent, children: [
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
            { path: '**', redirectTo: 'themes' }
        ]
    }
];
