import { Component, OnInit, Optional, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { REQUEST } from '@nguniversal/express-engine/tokens';
import { SafeStyle } from '@angular/platform-browser';
import { Store, select } from '@ngrx/store';
import { TranslateService } from '@ngx-translate/core';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from './core/store';

import { AlertLocation } from './core/store/alert';

import { selectStyle } from './core/store/theme';

import * as fromLanguage from './core/store/language/language.actions';
import * as fromMetadata from './core/store/metadata/metadata.actions';

import { environment } from '../environments/environment';

@Component({
    selector: 'scholars-root',
    templateUrl: 'app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    public style: Observable<SafeStyle>;

    public location = AlertLocation.MAIN;

    constructor(
        private store: Store<AppState>,
        private translate: TranslateService,
        @Optional()
        @Inject(REQUEST) private request: Request,
        @Inject(PLATFORM_ID) private platformId: any
    ) {
        this.translate.setDefaultLang(environment.language);
    }

    ngOnInit(): void {
        this.style = this.store.pipe(
            select(selectStyle),
            skipWhile((style: SafeStyle) => style === undefined)
        );
        this.store.dispatch(new fromMetadata.AddMetadataTagsAction({
            tags: [{
                name: 'title', content: 'Scholars'
            }]
        }));
        const language = this.getLang();
        if (['en'].indexOf(language) > -1) {
            this.store.dispatch(new fromLanguage.SetLanguageAction({ language }));
        }
    }

    public getLang(): string {
        let lang: string;
        if (isPlatformBrowser(this.platformId)) {
            lang = this.translate.getBrowserLang();
        } else {
            lang = (this.request.headers['accept-language'] || '').substring(0, 2);
        }
        return lang;
    }

}
