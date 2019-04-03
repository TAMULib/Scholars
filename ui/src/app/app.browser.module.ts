import { NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { REQUEST } from '@nguniversal/express-engine/tokens';
import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppModule } from './app.module';

import { AppComponent } from './app.component';

import { CustomMissingTranslationHandler } from './core/handler/custom-missing-translation.handler';

export function getRequest() {
    return { headers: { cookie: document.cookie } };
}

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, '/assets/i18n/', '.json');
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
                deps: [HttpClient]
            }
        })
    ],
    // Since the bootstrapped component is not inherited from your
    // imported AppModule, it needs to be repeated here.
    bootstrap: [
        AppComponent
    ],
    providers: [
        { provide: REQUEST, useFactory: (getRequest) }
    ]
})
export class AppBrowserModule {

}
