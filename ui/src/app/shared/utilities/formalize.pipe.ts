import { PipeTransform, Pipe } from '@angular/core';

import { environment } from '../../../environments/environment';

export const formalize = (value) => {
    for (const key in environment.formalize) {
        if (environment.formalize.hasOwnProperty(key) && value === key) {
            value = environment.formalize[key];
        }
    }
    value = value.replace(/([A-Z])/g, ' $1').replace(/^./, (str) => str.toUpperCase());
    return value;
};

@Pipe({ name: 'formalize' })
export class FormalizePipe implements PipeTransform {

    transform(value) {
        return formalize(value);
    }

}
