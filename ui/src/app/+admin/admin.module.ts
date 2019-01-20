import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MissingTranslationHandler, TranslateModule } from '@ngx-translate/core';

import { SharedModule } from '../shared/shared.module';

import { CustomMissingTranslationHandler } from '../core/handler/custom-missing-translation.handler';

import { AdminComponent } from './admin.component';
import { ThemesComponent } from './themes/themes.component';
import { UsersComponent } from './users/users.component';

import { routes } from './admin.routes';

@NgModule({
    declarations: [
        AdminComponent,
        ThemesComponent,
        UsersComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        TranslateModule.forChild({
            missingTranslationHandler: {
                provide: MissingTranslationHandler,
                useClass: CustomMissingTranslationHandler
            },
            isolate: false
        }),
        RouterModule.forChild(routes)
    ]
})
export class AdminModule {

    public static routes = routes;

}
