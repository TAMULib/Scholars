import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo/abstract-sdr-repo';
import { User } from '../user';

@Injectable({
    providedIn: 'root',
})
export class UserRepo extends AbstractSdrRepo<User> {

    protected path(): string {
        return 'users';
    }

}
