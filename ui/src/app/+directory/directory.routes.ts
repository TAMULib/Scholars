import { Routes } from '@angular/router';

import { DirectoryComponent } from './direcotory.component';

export const routes: Routes = [
    {
        path: '', component: DirectoryComponent, pathMatch: 'full'
    }
];
