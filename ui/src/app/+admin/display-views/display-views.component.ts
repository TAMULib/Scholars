import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SdrPage } from '../../core/model/sdr';
import { DisplayView } from '../../core/model/view';

import { selectAllResources, selectResourcesPage } from '../../core/store/sdr';

@Component({
    selector: 'scholars-display-views',
    templateUrl: './display-views.component.html',
    styleUrls: ['./display-views.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class DisplayViewsComponent implements OnInit {

    public displayViews: Observable<DisplayView[]>;

    public page: Observable<SdrPage>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.displayViews = this.store.pipe(select(selectAllResources<DisplayView>('displayViews')));
        this.page = this.store.pipe(select(selectResourcesPage<DisplayView>('displayViews')));
    }

}
