import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { RestService } from './rest.service';

import { environment } from '../../../environments/environment';
import { SdrCollection, SdrPageRequest } from '../model/sdr';

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

}
