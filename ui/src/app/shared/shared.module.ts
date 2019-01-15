import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AlertComponent } from './alert/alert.component';
import { DialogComponent } from './dialog/dialog.component';
import { StatsBoxComponent } from './stats-box/stats-box.component';
import { LoginComponent } from './dialog/login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { PaginationComponent } from './pagination/pagination.component';
import { RecentPublicationsComponent } from './recent-publications/recent-publications.component';
import { RegistrationComponent } from './dialog/registration/registration.component';
import { SearchBoxComponent } from './search-box/search-box.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { UserEditComponent } from './dialog/user-edit/user-edit.component';

const MODULES = [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
];

const PIPES = [

];

const COMPONENTS = [
    AlertComponent,
    DialogComponent,
    StatsBoxComponent,
    LoginComponent,
    NavigationComponent,
    PaginationComponent,
    RecentPublicationsComponent,
    RegistrationComponent,
    SearchBoxComponent,
    SidebarComponent
];

const ENTRY_COMPONENTS = [
    LoginComponent,
    RegistrationComponent,
    UserEditComponent
];

@NgModule({
    declarations: [
        ...PIPES,
        ...COMPONENTS,
        ...ENTRY_COMPONENTS
    ],
    entryComponents: [
        ...ENTRY_COMPONENTS
    ],
    exports: [
        ...MODULES,
        ...PIPES,
        ...COMPONENTS
    ],
    imports: [
        ...MODULES
    ]
})
export class SharedModule {

}
