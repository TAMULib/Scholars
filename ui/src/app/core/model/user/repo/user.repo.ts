import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo/abstract-sdr-repo';
import { User } from '../user';
import { SdrCollection, Count } from '../../sdr';
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

    public count(request: SdrRequest): Observable<Count> {
        throw new Error('Users does not support count!');
    }

    public findByTypesIn(types: string[]): Observable<User> {
        throw new Error('Users does not support find by types in!');
    }

    public findByIdIn(ids: string[]): Observable<SdrCollection> {
        throw new Error('Users does not support find by id in!');
    }

}
