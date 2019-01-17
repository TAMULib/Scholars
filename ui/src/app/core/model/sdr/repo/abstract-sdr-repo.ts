import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { RestService } from '../../../service/rest.service';
import { SdrRepo } from './sdr-repo';

import { SdrCollection } from '../sdr-collection';
import { SdrResource } from '../sdr-resource';
import { SdrPageRequest } from '../sdr-page';

import { environment } from '../../../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export abstract class AbstractSdrRepo<R extends SdrResource> implements SdrRepo<R> {

    constructor(protected restService: RestService) {

    }

    public page(page: SdrPageRequest): Observable<SdrCollection> {
        return this.restService.get<SdrCollection>(`${environment.service}/${this.path()}?page=${page.number - 1}&size=${page.size}`, {
            withCredentials: true
        });
    }

    public getAll(): Observable<SdrCollection> {
        return this.restService.get<SdrCollection>(`${environment.service}/${this.path()}`, {
            withCredentials: true
        });
    }

    public getOne(id: string | number): Observable<R> {
        return this.restService.get<R>(`${environment.service}/${this.path()}/${id}`, {
            withCredentials: true
        });
    }

    public post(resource: R): Observable<R> {
        return this.restService.post<R>(`${environment.service}/${this.path()}`, resource, { withCredentials: true });
    }

    public put(resource: R): Observable<R> {
        return this.restService.put<R>(resource._links.self.href, resource, { withCredentials: true });
    }

    public patch(resource: R): Observable<R> {
        return this.restService.patch<R>(resource._links.self.href, resource, { withCredentials: true });
    }

    public delete(resource: R): Observable<string> {
        return this.restService.delete<string>(resource._links.self.href, {
            withCredentials: true,
            responseType: 'text'
        });
    }

    protected abstract path(): string;

}
