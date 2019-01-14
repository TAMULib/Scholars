import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';

import { AboutComponent } from './about/about.component';
import { DashboardComponent } from './dashboard.component';
import { DirectoryComponent } from './directory/directory.component';
import { HomeComponent } from './home/home.component';
import { OrganizationsComponent } from './organizations/organizations.component';
import { PeopleComponent } from './people/people.component';
import { ResearchComponent } from './research/research.component';

import { routes } from './dashboard.routes';

@NgModule({
    declarations: [
        AboutComponent,
        DashboardComponent,
        DirectoryComponent,
        HomeComponent,
        OrganizationsComponent,
        PeopleComponent,
        ResearchComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        RouterModule.forChild(routes)
    ]
})
export class DashboardModule {

    public static routes = routes;

}
