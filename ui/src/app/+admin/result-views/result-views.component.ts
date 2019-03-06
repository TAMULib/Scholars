import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { DialogService } from '../../core/service/dialog.service';

import { AppState } from '../../core/store';
import { SdrPage, SdrPageRequest } from '../../core/model/sdr';
import { ResultView } from '../../core/model/view';

import { selectAllResources, selectResourcesPage } from '../../core/store/sdr';

import * as fromSdr from '../../core/store/sdr/sdr.actions';


@Component({
    selector: 'scholars-result-views',
    templateUrl: './result-views.component.html',
    styleUrls: ['./result-views.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ResultViewsComponent implements OnInit {

    public resultViews: Observable<ResultView[]>;

    public page: Observable<SdrPage>;

    constructor(
        private store: Store<AppState>,
        private dialog: DialogService
    ) {

    }

    ngOnInit() {
        this.resultViews = this.store.pipe(select(selectAllResources<ResultView>('resultViews')));
        this.page = this.store.pipe(select(selectResourcesPage<ResultView>('resultViews')));
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromSdr.PageResourcesAction('resultViews', { page }));
    }

}
