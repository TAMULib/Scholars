import { Injectable } from '@angular/core';

import { AbstractSdrDiscoverRepo } from '../../sdr/repo/abstract-sdr-discover-repo';
import { Organization } from '../organization';

@Injectable({
    providedIn: 'root',
})
export class OrganizationRepo extends AbstractSdrDiscoverRepo<Organization> {

    protected path(): string {
        return 'organizations';
    }

}
