import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Person } from '../person';

@Injectable({
    providedIn: 'root',
})
export class PersonRepo extends AbstractSdrRepo<Person> {

    protected path(): string {
        return 'persons';
    }

    public post(person: Person): Observable<Person> {
        throw new Error('Persons does not support post!');
    }

    public put(person: Person): Observable<Person> {
        throw new Error('Persons does not support put!');
    }

    public patch(person: Person): Observable<Person> {
        throw new Error('Persons does not support patch!');
    }

    public delete(person: Person): Observable<string> {
        throw new Error('Persons does not support delete!');
    }

    public findByTypesIn(types: string[]): Observable<Person> {
        throw new Error('Persons does not support find by types in!');
    }

}
