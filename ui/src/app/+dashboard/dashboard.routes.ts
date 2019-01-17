import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { PeopleComponent } from './people/people.component';
import { OrganizationsComponent } from './organizations/organizations.component';
import { ResearchComponent } from './research/research.component';
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
                path: 'people', component: PeopleComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Directory - People' }]
                }
            },
            {
                path: 'organizations', component: OrganizationsComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Directory - Organizations' }]
                }
            },
            {
                path: 'research', component: ResearchComponent, data: {
                    tags: [{ name: 'view', content: 'Scholars Directory - Research' }]
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
