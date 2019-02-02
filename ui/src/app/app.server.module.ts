import { NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ModuleMapLoaderModule } from '@nguniversal/module-map-ngfactory-loader';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ServerModule, ServerTransferStateModule } from '@angular/platform-server';

import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';

import { Observable, Observer } from 'rxjs';

import { readFileSync } from 'fs';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';

import { CustomMissingTranslationHandler } from './core/handler/custom-missing-translation.handler';

export function createUniversalTranslateLoader(): TranslateLoader {
    return {
        getTranslation: (lang: string) => {
            return new Observable((observer: Observer<any>) => {
                observer.next(JSON.parse(readFileSync(`./dist/browser/assets/i18n/${lang}.json`, 'utf8')));
                observer.complete();
            });
        }
    } as TranslateLoader;
}

@NgModule({
    imports: [
        // The AppServerModule should import your AppModule followed
        // by the ServerModule from @angular/platform-server.
        AppModule,
        ServerModule,
        ModuleMapLoaderModule,
        ServerTransferStateModule,
        NoopAnimationsModule,
        TranslateModule.forRoot({
            missingTranslationHandler: {
                provide: MissingTranslationHandler,
                useClass: CustomMissingTranslationHandler
            },
            loader: {
                provide: TranslateLoader,
                useFactory: (createUniversalTranslateLoader),
                deps: [HttpClient]
            }
        })
    ],
    // Since the bootstrapped component is not inherited from your
    // imported AppModule, it needs to be repeated here.
    bootstrap: [
        AppComponent
    ]
})
export class AppServerModule {

}
