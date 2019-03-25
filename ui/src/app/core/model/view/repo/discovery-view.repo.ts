import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { ViewRepo } from './view.repo';

import { DiscoveryView } from '../';
import { SdrCollection } from '../../sdr';
import { SdrRequest } from '../../request';

@Injectable({
    providedIn: 'root',
})
export class DiscoveryViewRepo extends AbstractSdrRepo<DiscoveryView> implements ViewRepo<DiscoveryView> {

    protected path(): string {
        return 'discoveryViews';
    }

    public search(request: SdrRequest): Observable<SdrCollection> {
        throw new Error('Discovery Views does not support faceted search!');
    }

}
