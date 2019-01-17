import { Component, OnInit, Input } from '@angular/core';
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

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.dialog = {
            title: 'Notification',
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: 'Close',
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => of(false)
            }
        };
    }

}
