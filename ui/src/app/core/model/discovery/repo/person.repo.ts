import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Person } from '../person';

@Injectable({
    providedIn: 'root',
})
export class PersonRepo extends AbstractSdrRepo<Person> {

    protected path(): string {
        return 'persons';
    }

}
