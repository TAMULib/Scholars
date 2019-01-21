import { Component, OnInit } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Store, select } from '@ngrx/store';
import { TranslateService } from '@ngx-translate/core';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from './core/store';

import { AlertLocation } from './core/store/alert';

import { selectStyle } from './core/store/theme';

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
        private translate: TranslateService
    ) {
        this.translate.setDefaultLang(environment.language);
        this.translate.use(environment.language);
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
    }

}
