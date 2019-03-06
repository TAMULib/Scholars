import { Routes } from '@angular/router';

import { DiscoveryComponent } from './discovery.component';

export const routes: Routes = [
    {
        path: ':collection', component: DiscoveryComponent, pathMatch: 'full'
    },
    { path: '**', redirectTo: 'persons' }
];
