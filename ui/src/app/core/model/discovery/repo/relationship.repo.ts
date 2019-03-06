import { Injectable } from '@angular/core';

import { AbstractSdrDiscoverRepo } from '../../sdr/repo/abstract-sdr-discover-repo';
import { Relationship } from '../relationship';

@Injectable({
    providedIn: 'root',
})
export class RelationshipRepo extends AbstractSdrDiscoverRepo<Relationship> {

    protected path(): string {
        return 'relationships';
    }

}
