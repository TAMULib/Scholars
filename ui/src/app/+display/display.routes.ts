import { Routes } from '@angular/router';

import { DisplayComponent } from './display.component';

export const routes: Routes = [
    {
        path: ':collection/:id', component: DisplayComponent, pathMatch: 'full'
    }
];
