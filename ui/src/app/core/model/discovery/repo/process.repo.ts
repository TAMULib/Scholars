import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Process } from '../process';

@Injectable({
    providedIn: 'root',
})
export class ProcessRepo extends AbstractSdrRepo<Process> {

    protected path(): string {
        return 'processes';
    }

}
