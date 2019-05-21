import { PipeTransform, Pipe } from '@angular/core';

export const formalize = (value) => {
    if (value === 'otherUniversity') {
        value = 'ExternalOrganization';
    }
    if (value === 'GreyLiterature') {
        value = 'InstitutionalRepositoryDocument';
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
