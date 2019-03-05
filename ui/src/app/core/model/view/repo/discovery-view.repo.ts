import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { ViewRepo } from './view.repo';

import { DiscoveryView } from '../';

@Injectable({
    providedIn: 'root',
})
export class DiscoveryViewRepo extends AbstractSdrRepo<DiscoveryView> implements ViewRepo<DiscoveryView> {

    protected path(): string {
        return 'discoveryViews';
    }

}
