import { Routes } from '@angular/router';

import { AuthGuard } from './core/guard/auth.guard';
import { Role } from './core/model/user';

export const routes: Routes = [
    {
        path: 'admin', loadChildren: './+admin#AdminModule', canActivate: [AuthGuard], data: {
            roles: [Role.ROLE_SUPER_ADMIN, Role.ROLE_ADMIN],
            tags: [
                { name: 'view', content: 'Scholars Administration' }
            ]
        }
    },
    {
        path: 'directory', loadChildren: './+directory#DirectoryModule', canActivate: [], data: {
            tags: [
                { name: 'view', content: 'Scholars Directory' }
            ]
        }
    },
    {
        path: 'discovery', loadChildren: './+discovery#DiscoveryModule', canActivate: [], data: {
            tags: [
                { name: 'view', content: 'Scholars Discovery' }
            ]
        }
    },
    {
        path: 'display', loadChildren: './+display#DisplayModule', canActivate: [], data: {
            tags: [
                { name: 'view', content: 'Scholars Display' }
            ]
        }
    },
    {
        path: '', loadChildren: './+dashboard#DashboardModule', canActivate: [], data: {
            tags: [
                { name: 'title', content: 'Scholars' }
            ]
        }
    },
    { path: '**', redirectTo: '' }
];
