import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AlertComponent } from './alert/alert.component';
import { DialogComponent } from './dialog/dialog.component';
import { GridViewComponent } from './grid-view/grid-view.component';
import { ListViewComponent } from './list-view/list-view.component';
import { LoginComponent } from './dialog/login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { NotificationComponent } from './dialog/notification/notification.component';
import { PaginationComponent } from './pagination/pagination.component';
import { RecentPublicationsComponent } from './recent-publications/recent-publications.component';
import { ResultViewComponent } from './result-view/result-view.component';
import { RegistrationComponent } from './dialog/registration/registration.component';
import { SearchBoxComponent } from './search-box/search-box.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { UserEditComponent } from './dialog/user-edit/user-edit.component';
import { StatsBoxComponent } from './stats-box/stats-box.component';

import { LoaderDirective } from './loader/loader.directive';
import { SafeHtmlPipe } from './safe-html/safe-html.pipe';

const MODULES = [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    RouterModule
];

const PIPES = [
    SafeHtmlPipe
];

const COMPONENTS = [
    AlertComponent,
    DialogComponent,
    GridViewComponent,
    ListViewComponent,
    LoginComponent,
    NavigationComponent,
    PaginationComponent,
    RecentPublicationsComponent,
    RegistrationComponent,
    ResultViewComponent,
    SearchBoxComponent,
    SidebarComponent,
    StatsBoxComponent
];

const DIRECTIVES = [
    LoaderDirective
];

const ENTRY_COMPONENTS = [
    LoginComponent,
    RegistrationComponent,
    NotificationComponent,
    UserEditComponent
];

@NgModule({
    declarations: [
        ...PIPES,
        ...COMPONENTS,
        ...DIRECTIVES,
        ...ENTRY_COMPONENTS
    ],
    entryComponents: [
        ...ENTRY_COMPONENTS
    ],
    exports: [
        ...MODULES,
        ...PIPES,
        ...COMPONENTS,
        ...DIRECTIVES
    ],
    imports: [
        ...MODULES
    ]
})
export class SharedModule {

}
