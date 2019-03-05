import { Injectable } from '@angular/core';

import { AbstractSdrDiscoverRepo } from '../../sdr/repo/abstract-sdr-discover-repo';
import { Concept } from '../concept';

@Injectable({
    providedIn: 'root',
})
export class ConceptRepo extends AbstractSdrDiscoverRepo<Concept> {

    protected path(): string {
        return 'concepts';
    }

}
