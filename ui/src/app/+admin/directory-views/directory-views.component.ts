import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { DialogService } from '../../core/service/dialog.service';

import { AppState } from '../../core/store';
import { SdrPage, SdrPageRequest } from '../../core/model/sdr';
import { DirectoryView } from '../../core/model/view';

import { selectAllResources, selectReousrcesPage } from '../../core/store/sdr';

import * as fromSdr from '../../core/store/sdr/sdr.actions';


@Component({
    selector: 'scholars-directory-views',
    templateUrl: './directory-views.component.html',
    styleUrls: ['./directory-views.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class DirectoryViewsComponent implements OnInit {

    public directoryViews: Observable<DirectoryView[]>;

    public page: Observable<SdrPage>;

    constructor(
        private store: Store<AppState>,
        private dialog: DialogService
    ) {

    }

    ngOnInit() {
        this.directoryViews = this.store.pipe(select(selectAllResources<DirectoryView>('directoryViews')));
        this.page = this.store.pipe(select(selectReousrcesPage<DirectoryView>('directoryViews')));
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromSdr.PageResourcesAction('directoryViews', { page }));
    }

}
