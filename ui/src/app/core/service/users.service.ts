import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { RestService } from './rest.service';

import { SdrCollection, SdrPageRequest } from '../model/sdr';
import { User } from '../model/user';

import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class UsersService {

    constructor(private restService: RestService) {

    }

    public getPage(page: SdrPageRequest): Observable<SdrCollection> {
        return this.restService.get<SdrCollection>(environment.service + `/users?page=${page.number - 1}&size=${page.size}`, {
            withCredentials: true
        });
    }

    public patchUser(user: User): Observable<User> {
        return this.restService.patch<User>(user._links.self.href, {
            role: user.role,
            enabled: user.enabled
        }, { withCredentials: true });
    }

    public deleteUser(user: User): Observable<string> {
        return this.restService.delete<string>(user._links.self.href, {
            withCredentials: true,
            responseType: 'text'
        });
    }


}
