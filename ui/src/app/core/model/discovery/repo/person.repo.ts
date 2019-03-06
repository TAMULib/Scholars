import { Injectable } from '@angular/core';

import { AbstractSdrDiscoverRepo } from '../../sdr/repo/abstract-sdr-discover-repo';
import { Person } from '../person';

@Injectable({
    providedIn: 'root',
})
export class PersonRepo extends AbstractSdrDiscoverRepo<Person> {

    protected path(): string {
        return 'persons';
    }

}
