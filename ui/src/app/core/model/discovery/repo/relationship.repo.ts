import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Relationship } from '../relationship';

@Injectable({
    providedIn: 'root',
})
export class RelationshipRepo extends AbstractSdrRepo<Relationship> {

    protected path(): string {
        return 'relationships';
    }

}
