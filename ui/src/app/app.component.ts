import { Component, OnInit } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from './core/store';

import { AlertLocation } from './core/store/alert/alert.model';

import { selectStyle } from './core/store/themes';

import * as fromMetadata from './core/store/metadata/metadata.actions';

@Component({
    selector: 'scholars-root',
    templateUrl: 'app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    public style: Observable<SafeStyle>;

    public location = AlertLocation.MAIN;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
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
