import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { ViewRepo } from './view.repo';

import { DirectoryView } from '../';
import { SdrCollection, Count } from '../../sdr';
import { SdrRequest } from '../../request';

@Injectable({
    providedIn: 'root',
})
export class DirectoryViewRepo extends AbstractSdrRepo<DirectoryView> implements ViewRepo<DirectoryView> {

    protected path(): string {
        return 'directoryViews';
    }

    public search(request: SdrRequest): Observable<SdrCollection> {
        throw new Error('Directory Views does not support faceted search!');
    }

    public count(request: SdrRequest): Observable<Count> {
        throw new Error('Directory Views does not support count!');
    }

    public findByTypesIn(types: string[]): Observable<DirectoryView> {
        throw new Error('Directory Views does not support find by types in!');
    }

    public findByIdIn(ids: string[]): Observable<SdrCollection> {
        throw new Error('Directory Views does not support find by id in!');
    }

}
