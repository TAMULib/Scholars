import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Concept } from '../concept';

@Injectable({
    providedIn: 'root',
})
export class ConceptRepo extends AbstractSdrRepo<Concept> {

    protected path(): string {
        return 'concepts';
    }

}
