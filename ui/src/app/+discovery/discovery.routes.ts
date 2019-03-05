import { Routes } from '@angular/router';

import { DiscoveryComponent } from './discovery.component';

export const routes: Routes = [
    {
        path: '', component: DiscoveryComponent, pathMatch: 'full'
    }
];
