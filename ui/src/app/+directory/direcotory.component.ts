import { Component } from '@angular/core';

import { Store } from '@ngrx/store';

import { AppState } from '../core/store';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent {

    constructor(private store: Store<AppState>) {

    }

}
