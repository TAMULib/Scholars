import { Type } from '@angular/core';

import { NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';

export type Dialog = Readonly<{
    ref: {
        component: Type<any>,
        inputs: any
    };
    options: NgbModalOptions;
}>;
