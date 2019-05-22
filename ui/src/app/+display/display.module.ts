import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MissingTranslationHandler, TranslateModule } from '@ngx-translate/core';

import { SharedModule } from '../shared/shared.module';

import { CustomMissingTranslationHandler } from '../core/handler/custom-missing-translation.handler';

import { DisplayComponent } from './display.component';
import { TabComponent } from './tab/tab.component';
import { SectionComponent } from './section/section.component';
import { SubsectionComponent } from './subsection/subsection.component';

import { routes } from './display.routes';

@NgModule({
    declarations: [
        DisplayComponent,
        TabComponent,
        SectionComponent,
        SubsectionComponent
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
export class DisplayModule {

    public static routes = routes;

}
