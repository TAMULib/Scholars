import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SdrPage, SdrPageRequest } from '../../core/model/sdr';
import { Theme } from '../../core/model/theme';

import { selectAllThemes, selectThemesPage } from '../../core/store/themes';

import * as fromThemes from '../../core/store/themes/themes.actions';

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
        this.themes = this.store.pipe(select(selectAllThemes));
        this.page = this.store.pipe(select(selectThemesPage));
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromThemes.LoadThemesAction({ page }));
    }

}
