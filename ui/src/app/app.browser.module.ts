import { NgModule } from '@angular/core';
import { DOCUMENT, APP_BASE_HREF } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppModule } from './app.module';

import { AppComponent } from './app.component';

import { ComputedStyleLoader } from './core/computed-style-loader';

import { CustomMissingTranslationHandler } from './core/handler/custom-missing-translation.handler';

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, 'assets/i18n/', '.json');
}

export function getRequest() {
    return { headers: { cookie: document.cookie } };
}

export function createStyleLoader(document: Document): ComputedStyleLoader {
    return {
        getComputedStyle(): any {
            return getComputedStyle(document.body);
        }
    } as ComputedStyleLoader;
}

export function getBaseHref(document: Document): string {
    const baseTag = document.querySelector('head > base')
    return baseTag.getAttribute('href');
}

@NgModule({
    imports: [
        BrowserModule.withServerTransition({ appId: 'scholars-discovery' }),
        AppModule,
        TranslateModule.forRoot({
            missingTranslationHandler: {
                provide: MissingTranslationHandler,
                useClass: CustomMissingTranslationHandler
            },
            loader: {
                provide: TranslateLoader,
                useFactory: (createTranslateLoader),
                deps: [HttpClient, APP_BASE_HREF]
            }
        })
    ],
    // Since the bootstrapped component is not inherited from your
    // imported AppModule, it needs to be repeated here.
    bootstrap: [
        AppComponent
    ],
    providers: [
        {
            provide: REQUEST,
            useFactory: (getRequest)
        },
        {
            provide: APP_BASE_HREF,
            useFactory: (getBaseHref),
            deps: [DOCUMENT]
        },
        {
            provide: ComputedStyleLoader,
            useFactory: (createStyleLoader),
            deps: [DOCUMENT]
        }
    ]
})
export class AppBrowserModule {

}
