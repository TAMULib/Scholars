import { Type } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';

import { Observable } from 'rxjs';

export enum DialogButtonType {
    PRIMARY = 'btn-primary',
    SECONDARY = 'btn-secondary',
    SUCCESS = 'btn-success',
    DANGER = 'btn-danger',
    WARNING = 'btn-warning',
    INFO = 'btn-info',
    LIGHT = 'btn-light',
    DARK = 'btn-dark',
    LINK = 'btn-link',
    OUTLINE_PRIMARY = 'btn-outline-primary',
    OUTLINE_SECONDARY = 'btn-outline-secondary',
    OUTLINE_SUCCESS = 'btn-outline-success',
    OUTLINE_DANGER = 'btn-outline-danger',
    OUTLINE_WARNING = 'btn-outline-warning',
    OUTLINE_INFO = 'btn-outline-info',
    OUTLINE_LIGHT = 'btn-outline-light',
    OUTLINE_DARK = 'btn-outline-dark',
}

export type DialogButton = Readonly<{
    type: DialogButtonType;
    label: string;
    action: () => any;
    disabled: () => Observable<boolean>;
}>;

export type DialogControl = Readonly<{
    title: string;
    form?: FormGroup;
    close: DialogButton;
    submit?: DialogButton;
}>;

export type Dialog = Readonly<{
    ref: {
        component: Type<any>,
        inputs: any
    };
    options: NgbModalOptions;
}>;
