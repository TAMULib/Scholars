import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Relationship } from '../relationship';

@Injectable({
    providedIn: 'root',
})
export class RelationshipRepo extends AbstractSdrRepo<Relationship> {

    protected path(): string {
        return 'relationships';
    }

    public post(relationship: Relationship): Observable<Relationship> {
        throw new Error('Relationships does not support post!');
    }

    public put(relationship: Relationship): Observable<Relationship> {
        throw new Error('Relationships does not support put!');
    }

    public patch(relationship: Relationship): Observable<Relationship> {
        throw new Error('Relationships does not support patch!');
    }

    public delete(relationship: Relationship): Observable<string> {
        throw new Error('Relationships does not support delete!');
    }

}
