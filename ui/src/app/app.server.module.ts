import { NgModule } from '@angular/core';
import { DOCUMENT, APP_BASE_HREF } from '@angular/common';
import { ModuleMapLoaderModule } from '@nguniversal/module-map-ngfactory-loader';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ServerModule, ServerTransferStateModule } from '@angular/platform-server';

import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';

import { Observable, Observer } from 'rxjs';

import { readFileSync } from 'fs';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';

import { ComputedStyleLoader } from './core/computed-style-loader';

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

export function createUniversalStyleLoader(document: Document, baseHref: string): ComputedStyleLoader {
    return {
        getComputedStyle(): any {
            const styleLinkTag = document.querySelector('head > link[rel=stylesheet]');
            const stylesheet = styleLinkTag.getAttribute('href');
            const styles = readFileSync(`./dist/browser/${stylesheet.replace(baseHref, '')}`, 'utf8');
            const root = styles.match(/:root{([^}]+)}/g)[0];
            const cssTxt = root.replace(/\/\*(.|\s)*?\*\//g, ' ').replace(/\s+/g, ' ');
            // tslint:disable-next-line:one-variable-per-declaration
            const style = {}, [, ruleName, rule] = cssTxt.match(/ ?(.*?) ?{([^}]*)}/) || [, , cssTxt];
            const properties = rule.split(';').map(o => o.split(':').map(x => x && x.trim()));
            for (const [property, value] of properties) { if (value) { style[property] = value; } }
            return { root, ruleName, style, getPropertyValue: (key) => style[key] };
        }
    } as ComputedStyleLoader;
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
                useFactory: (createUniversalTranslateLoader)
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
            provide: ComputedStyleLoader,
            useFactory: (createUniversalStyleLoader),
            deps: [DOCUMENT, APP_BASE_HREF]
        }
    ]
})
export class AppServerModule {

}
