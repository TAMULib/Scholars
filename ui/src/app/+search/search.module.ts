import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';

import { SearchComponent } from './search.component';

import { routes } from './search.routes';

@NgModule({
    declarations: [
        SearchComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        RouterModule.forChild(routes)
    ]
})
export class SearchModule {

    public static routes = routes;

}
