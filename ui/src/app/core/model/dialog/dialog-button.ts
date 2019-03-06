import { Observable } from 'rxjs';

import { DialogButtonType } from './dialog-button-type';

export type DialogButton = Readonly<{
    type: DialogButtonType;
    label: Observable<string>;
    action: () => any;
    disabled: () => Observable<boolean>;
}>;
