import { Component, Input } from '@angular/core';

import { AlertLocation } from '../../core/model/alert';
import { DialogControl } from '../../core/model/dialog';

@Component({
    selector: 'scholars-dialog',
    templateUrl: './dialog.component.html',
    styleUrls: ['./dialog.component.scss']
})
export class DialogComponent {

    public location = AlertLocation.DIALOG;

    @Input() dialog: DialogControl;

}
