import { ModuleWithProviders, NgModule, Optional, SkipSelf, COMPILER_OPTIONS, CompilerFactory, Compiler } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthGuard } from './guard/auth.guard';
import { AuthService } from './service/auth.service';
import { RestService } from './service/rest.service';
import { ThemeService } from './service/theme.service';
import { StompService } from './service/stomp.service';
import { MetadataService } from './service/metadata.service';

import { ThemeRepo } from './model/theme/repo/theme.repo';
import { UserRepo } from './model/user/repo/user.repo';
import { AlertService } from './service/alert.service';
import { DialogService } from './service/dialog.service';
import { ResultViewService } from './service/result-view.service';
import { JitCompilerFactory } from '@angular/platform-browser-dynamic';

export function createCompiler(compilerFactory: CompilerFactory) {
    return compilerFactory.createCompiler();
}

const MODULES = [
    CommonModule
];

const COMPONENTS = [

];

const PROVIDERS = [
    AuthGuard,
    AlertService,
    AuthService,
    DialogService,
    MetadataService,
    RestService,
    ResultViewService,
    StompService,
    ThemeService,
    ThemeRepo,
    UserRepo
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
                ...PROVIDERS,
                // Compiler is not included in AOT-compiled bundle.
                // Must explicitly provide compiler to be able to compile templates at runtime.
                { provide: COMPILER_OPTIONS, useValue: { useJit: true }, multi: true },
                { provide: CompilerFactory, useClass: JitCompilerFactory, deps: [COMPILER_OPTIONS] },
                { provide: Compiler, useFactory: createCompiler, deps: [CompilerFactory] }
            ]
        };
    }

    constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
        if (parentModule) {
            throw new Error('CoreModule is already loaded. Import it in the AppModule only');
        }
    }

}
