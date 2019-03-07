import { Routes } from '@angular/router';

import { DirectoryComponent } from './directory.component';

export const routes: Routes = [
    {
        path: ':name', component: DirectoryComponent, pathMatch: 'full'
    },
    // TODO: dynamic redirect to first directory view
    { path: '**', redirectTo: 'People' }
];
