import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { ViewRepo } from './view.repo';

import { DisplayView } from '../';
import { SdrCollection, Count } from '../../sdr';
import { SdrRequest } from '../../request';

@Injectable({
    providedIn: 'root',
})
export class DisplayViewRepo extends AbstractSdrRepo<DisplayView> implements ViewRepo<DisplayView> {

    protected path(): string {
        return 'displayViews';
    }

    public search(request: SdrRequest): Observable<SdrCollection> {
        throw new Error('Display Views does not support faceted search!');
    }

    public count(request: SdrRequest): Observable<Count> {
        throw new Error('Display Views does not support count!');
    }

    public findByIdIn(ids: string[]): Observable<SdrCollection> {
        throw new Error('Display Views does not support find by types in!');
    }

}
