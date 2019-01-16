import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthGuard } from './guard/auth.guard';
import { AuthService } from './service/auth.service';
import { RestService } from './service/rest.service';
import { ThemesService } from './service/themes.service';
import { UsersService } from './service/users.service';
import { StompService } from './service/stomp.service';
import { MetadataService } from './service/metadata.service';

const MODULES = [
    CommonModule
];

const COMPONENTS = [

];

const PROVIDERS = [
    AuthGuard,
    AuthService,
    MetadataService,
    RestService,
    StompService,
    ThemesService,
    UsersService
];

@NgModule({
    imports: [
        ...MODULES
    ],
    exports: [
        ...COMPONENTS
    ],
    declarations: [
        ...COMPONENTS
    ],
    providers: [
        ...PROVIDERS
    ]
})
export class CoreModule {

    public static forRoot(): ModuleWithProviders {
        return {
            ngModule: CoreModule,
            providers: [
                ...PROVIDERS
            ]
        };
    }

    constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
        if (parentModule) {
            throw new Error('CoreModule is already loaded. Import it in the AppModule only');
        }
    }

}
