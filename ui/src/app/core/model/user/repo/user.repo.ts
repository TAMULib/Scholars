import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo/abstract-sdr-repo';
import { User } from '../user';
import { SdrCollection } from '../../sdr';
import { SdrRequest } from '../../request';

@Injectable({
    providedIn: 'root',
})
export class UserRepo extends AbstractSdrRepo<User> {

    protected path(): string {
        return 'users';
    }

    public search(request: SdrRequest): Observable<SdrCollection> {
        throw new Error('Users does not support faceted search!');
    }

}
