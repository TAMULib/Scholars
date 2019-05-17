import { PipeTransform, Pipe } from '@angular/core';

@Pipe({ name: 'formalize' })
export class FormalizePipe implements PipeTransform {

    transform(value) {
        return value.replace(/([A-Z])/g, ' $1').replace(/^./, (str) => str.toUpperCase());
    }

}
