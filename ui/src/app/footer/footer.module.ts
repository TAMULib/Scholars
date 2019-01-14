import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SharedModule } from '../shared/shared.module';
import { FooterComponent } from './footer.component';
import { CopyrightComponent } from './copyright/copyright.component';

@NgModule({
    declarations: [
        FooterComponent,
        CopyrightComponent
    ],
    imports: [
        CommonModule,
        SharedModule
    ],
    exports: [
        FooterComponent
    ]
})
export class FooterModule {

}
