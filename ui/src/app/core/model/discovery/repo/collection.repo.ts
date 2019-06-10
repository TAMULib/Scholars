import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Collection } from '../collection';

@Injectable({
    providedIn: 'root',
})
export class CollectionRepo extends AbstractSdrRepo<Collection> {

    protected path(): string {
        return 'collections';
    }

    public post(collection: Collection): Observable<Collection> {
        throw new Error('Collections does not support post!');
    }

    public put(collection: Collection): Observable<Collection> {
        throw new Error('Collections does not support put!');
    }

    public patch(collection: Collection): Observable<Collection> {
        throw new Error('Collections does not support patch!');
    }

    public delete(collection: Collection): Observable<string> {
        throw new Error('Collections does not support delete!');
    }

    public findByTypesIn(types: string[]): Observable<Collection> {
        throw new Error('Collections does not support find by types in!');
    }

}
