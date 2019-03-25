import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Organization } from '../organization';

@Injectable({
    providedIn: 'root',
})
export class OrganizationRepo extends AbstractSdrRepo<Organization> {

    protected path(): string {
        return 'organizations';
    }

    public post(organization: Organization): Observable<Organization> {
        throw new Error('Organizations does not support post!');
    }

    public put(organization: Organization): Observable<Organization> {
        throw new Error('Organizations does not support put!');
    }

    public patch(organization: Organization): Observable<Organization> {
        throw new Error('Organizations does not support patch!');
    }

    public delete(organization: Organization): Observable<string> {
        throw new Error('Organizations does not support delete!');
    }

}
