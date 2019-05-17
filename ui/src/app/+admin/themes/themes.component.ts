import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SdrPage } from '../../core/model/sdr';
import { Theme } from '../../core/model/theme';

import { selectAllResources, selectResourcesPage } from '../../core/store/sdr';

@Component({
    selector: 'scholars-themes',
    templateUrl: './themes.component.html',
    styleUrls: ['./themes.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ThemesComponent implements OnInit {

    public themes: Observable<Theme[]>;

    public page: Observable<SdrPage>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.themes = this.store.pipe(select(selectAllResources<Theme>('themes')));
        this.page = this.store.pipe(select(selectResourcesPage<Theme>('themes')));
    }

}
