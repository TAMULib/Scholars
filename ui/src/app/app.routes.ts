import { Routes } from '@angular/router';

import { AuthGuard } from './core/guard/auth.guard';
import { Role } from './core/model/user';

export const routes: Routes = [
    {
        path: 'admin', loadChildren: './+admin#AdminModule', canActivate: [AuthGuard], data: {
            roles: [Role.ROLE_SUPER_ADMIN, Role.ROLE_ADMIN],
            tags: [{ name: 'view', content: 'Scholars Administration' }]
        }
    },
    {
        path: 'search', loadChildren: './+search#SearchModule', canActivate: [], data: {
            tags: [{ name: 'view', content: 'Scholars Search' }]
        }
    },
    {
        path: '', loadChildren: './+dashboard#DashboardModule', canActivate: [], data: {
            tags: [{ name: 'view', content: 'Scholars Dashboard' }]
        }
    },
    { path: '**', redirectTo: '' }
];
