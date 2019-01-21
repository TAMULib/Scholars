import { Component, OnInit, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Store } from '@ngrx/store';

import { of } from 'rxjs';

import { AppState } from '../../../core/store';
import { DialogButtonType, DialogControl } from '../../../core/store/dialog';

import * as fromDialog from '../../../core/store/dialog/dialog.actions';

@Component({
    selector: 'scholars-notification',
    templateUrl: './notification.component.html',
    styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {

    @Input() text: string;

    public dialog: DialogControl;

    constructor(
        private translate: TranslateService,
        private store: Store<AppState>
    ) {

    }

    ngOnInit() {
        this.dialog = {
            title: this.translate.get('SHARED.DIALOG.NOTIFICATION.TITLE'),
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: this.translate.get('SHARED.DIALOG.NOTIFICATION.CLOSE'),
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => of(false)
            }
        };
    }

}
