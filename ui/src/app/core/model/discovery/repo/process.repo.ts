import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Process } from '../process';

@Injectable({
    providedIn: 'root',
})
export class ProcessRepo extends AbstractSdrRepo<Process> {

    protected path(): string {
        return 'processes';
    }

    public post(process: Process): Observable<Process> {
        throw new Error('Processes does not support post!');
    }

    public put(process: Process): Observable<Process> {
        throw new Error('Processes does not support put!');
    }

    public patch(process: Process): Observable<Process> {
        throw new Error('Processes does not support patch!');
    }

    public delete(process: Process): Observable<string> {
        throw new Error('Processes does not support delete!');
    }

    public findByTypesIn(types: string[]): Observable<Process> {
        throw new Error('Processes does not support find by types in!');
    }

}
