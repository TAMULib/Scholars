import { Routes } from '@angular/router';

import { AuthGuard } from './core/guard/auth.guard';
import { Role } from './core/model/user';

export const routes: Routes = [
    {
        path: 'admin', loadChildren: './+admin#AdminModule', canActivate: [AuthGuard], data: {
            roles: [Role.ROLE_SUPER_ADMIN, Role.ROLE_ADMIN]
        }
    },
    { path: 'search', loadChildren: './+search#SearchModule', canActivate: [], data: {} },
    { path: '', loadChildren: './+dashboard#DashboardModule', canActivate: [], data: {} },
    { path: '**', redirectTo: '' }
];
