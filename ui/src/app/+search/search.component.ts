import { Component, OnInit } from '@angular/core';

import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../core/store';

import { selectRouterSearchQuery } from '../core/store/router';

@Component({
    selector: 'scholars-search',
    templateUrl: 'search.component.html',
    styleUrls: ['search.component.scss']
})
export class SearchComponent implements OnInit {

    public live = true;

    public query: Observable<string>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.query = this.store.pipe(select(selectRouterSearchQuery));
    }

}
