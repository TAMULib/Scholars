import { Injectable } from '@angular/core';

import { AbstractSdrDiscoverRepo } from '../../sdr/repo/abstract-sdr-discover-repo';
import { Process } from '../process';

@Injectable({
    providedIn: 'root',
})
export class ProcessRepo extends AbstractSdrDiscoverRepo<Process> {

    protected path(): string {
        return 'processes';
    }

}
