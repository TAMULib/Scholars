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
            { path: '', component: HomeComponent },
            { path: 'people', component: PeopleComponent },
            { path: 'organizations', component: OrganizationsComponent },
            { path: 'research', component: ResearchComponent },
            { path: 'about', component: AboutComponent },
            { path: '**', redirectTo: '' }
        ]
    }
];
