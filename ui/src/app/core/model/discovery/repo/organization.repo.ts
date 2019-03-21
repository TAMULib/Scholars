import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Organization } from '../organization';

@Injectable({
    providedIn: 'root',
})
export class OrganizationRepo extends AbstractSdrRepo<Organization> {

    protected path(): string {
        return 'organizations';
    }

}
