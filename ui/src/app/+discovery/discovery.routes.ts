import { Routes } from '@angular/router';

import { DiscoveryComponent } from './discovery.component';

export const routes: Routes = [
    {
        path: ':view', component: DiscoveryComponent, pathMatch: 'full'
    },
    // TODO: dynamic redirect to first discovery view
    { path: '**', redirectTo: 'People' }
];
