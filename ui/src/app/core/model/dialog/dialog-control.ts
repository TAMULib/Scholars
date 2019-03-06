import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

import { DialogButton } from './dialog-button';

export type DialogControl = Readonly<{
    title: Observable<string>;
    form?: FormGroup;
    close: DialogButton;
    submit?: DialogButton;
}>;
