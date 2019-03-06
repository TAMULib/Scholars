import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';

export const routes: Routes = [
    {
        path: '', component: DashboardComponent, children: [
            {
                path: '', component: HomeComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Home' }]
                }
            },
            {
                path: 'about', component: AboutComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars About' }]
                }
            },
            { path: '**', redirectTo: '' }
        ]
    }
];
