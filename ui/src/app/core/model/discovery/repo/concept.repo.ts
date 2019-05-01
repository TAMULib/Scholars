import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Concept } from '../concept';

@Injectable({
    providedIn: 'root',
})
export class ConceptRepo extends AbstractSdrRepo<Concept> {

    protected path(): string {
        return 'concepts';
    }

    public post(concept: Concept): Observable<Concept> {
        throw new Error('Concepts does not support post!');
    }

    public put(concept: Concept): Observable<Concept> {
        throw new Error('Concepts does not support put!');
    }

    public patch(concept: Concept): Observable<Concept> {
        throw new Error('Concepts does not support patch!');
    }

    public delete(concept: Concept): Observable<string> {
        throw new Error('Concepts does not support delete!');
    }

    public findByTypesIn(types: string[]): Observable<Concept> {
        throw new Error('Concepts does not support find by types in!');
    }

}
