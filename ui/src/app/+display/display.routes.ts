import { Routes } from '@angular/router';

import { DisplayComponent } from './display.component';
import { TabComponent } from './tab/tab.component';

export const routes: Routes = [
    {
        path: ':collection/:id',
        component: DisplayComponent,
        children: [
            { path: ':view/:tab', component: TabComponent }
        ]
    }
];
