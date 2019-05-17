import { Component, OnInit, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Params } from '@angular/router';
import { Store } from '@ngrx/store';

import { scheduled } from 'rxjs';
import { queue } from 'rxjs/internal/scheduler/queue';

import { AppState } from '../../../core/store';
import { DialogButtonType, DialogControl } from '../../../core/model/dialog';
import { SdrFacet } from '../../../core/model/sdr';

import * as fromDialog from '../../../core/store/dialog/dialog.actions';

@Component({
    selector: 'scholars-facet-entries',
    templateUrl: './facet-entries.component.html',
    styleUrls: ['./facet-entries.component.scss']
})
export class FacetEntriesComponent implements OnInit {

    @Input() name: string;

    @Input() facet: SdrFacet;

    public page = 2;

    public size = 10;

    public routerLink = [];

    public dialog: DialogControl;

    constructor(
        private translate: TranslateService,
        private store: Store<AppState>
    ) {

    }

    ngOnInit() {
        this.dialog = {
            title: scheduled([this.name], queue),
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: this.translate.get('SHARED.DIALOG.FACET_ENTRIES.CANCEL'),
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => scheduled([false], queue)
            }
        };
    }

    public getFacetEntryPage(): any[] {
        const start = (this.page - 1) * this.size;
        return this.facet.entries.slice(start, start + this.size);
    }

    public getQueryParams(facet: SdrFacet, entry: any): Params {
        const queryParams: Params = {};
        queryParams[`${facet.field}.filter`] = entry.value;
        return queryParams;
    }

}
