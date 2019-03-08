import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { RestService } from '../../../service/rest.service';
import { SdrRepo } from './sdr-repo';

import { SdrRequest } from '../../request';
import { SdrResource } from '../sdr-resource';
import { SdrCollection } from '../sdr-collection';

import { environment } from '../../../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export abstract class AbstractSdrRepo<R extends SdrResource> implements SdrRepo<R> {

    constructor(protected restService: RestService) {

    }

    public search(request: SdrRequest): Observable<SdrCollection> {
        return this.restService.get<SdrCollection>(`${environment.service}/${this.path()}/search/facet${this.mapParameters(request)}`, {
            withCredentials: true
        });
    }

    public page(request: SdrRequest): Observable<SdrCollection> {
        return this.restService.get<SdrCollection>(`${environment.service}/${this.path()}${this.mapParameters(request)}`, {
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

    protected mapParameters(request: SdrRequest): String {
        const parameters: string[] = [];

        parameters.push(`page=${(request.number)}`);
        parameters.push(`size=${request.size}`);

        if (request.sort) {
            console.log(request.sort);
            if (request.sort.name) {
                parameters.push(`sort=${encodeURIComponent(request.sort.name) + (request.sort.ascend === true ? ',asc' : request.sort.ascend === false ? ',desc' : '')}`);
            } else if (request.sort.ascend === true) {
                parameters.push('sort=asc');
            } else if (request.sort.ascend === false) {
                parameters.push('sort=desc');
            }
        }

        if (request.query) {
            console.log(request.query);
            parameters.push(`query=${encodeURIComponent(request.query)}`);
        }

        if (request.facets) {
            console.log(request.facets);
            parameters.push(`facets=${encodeURIComponent(request.facets.join())}`);

            request.facets.forEach((facet: string) => {
                console.log(facet);

                if (request[facet].filter) {
                    parameters.push(`type.filter=${encodeURIComponent(request[facet].filter)}`);
                }

                if (request[facet]) {
                    ['limit', 'offset', 'sort'].forEach((key: string) => {
                        if (request[facet][key]) {
                            parameters.push(`${facet}.limit=${request[facet][key]}`);
                        }
                    });
                }
            });
        }

        return `?${parameters.join('&')}`;
    }

    protected abstract path(): string;

}
