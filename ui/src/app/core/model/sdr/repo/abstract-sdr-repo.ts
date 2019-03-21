import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { RestService } from '../../../service/rest.service';
import { SdrRepo } from './sdr-repo';

import { Sort, Facetable, SdrRequest } from '../../request';
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

        if (request.pageable) {
            if (request.pageable.number) {
                parameters.push(`page=${(request.pageable.number)}`);
            }
            if (request.pageable.size) {
                parameters.push(`size=${request.pageable.size}`);
            }
            if (request.pageable.sort && request.pageable.sort.length > 0) {
                request.pageable.sort.forEach((sort: Sort) => {
                    parameters.push(`sort=${encodeURIComponent(sort.name)},${sort.direction}`);
                });
            }
        }

        if (request.query && request.query.length > 0) {
            parameters.push(`query=${encodeURIComponent(request.query)}`);
        }

        if (request.indexable) {
            parameters.push(`index=${encodeURIComponent(request.indexable.field)},${request.indexable.operationKey},${request.indexable.option}`);
        }

        if (request.facets && request.facets.length > 0) {
            const fields: string[] = [];
            request.facets.forEach((facet: Facetable) => {
                fields.push(facet.field);
                ['limit', 'offset', 'sort'].forEach((key: string) => {
                    if (facet[key]) {
                        parameters.push(`${facet.field}.${key}=${facet[key]}`);
                    }
                });
                if (facet.filter) {
                    parameters.push(`${facet.field}.filter=${encodeURIComponent(facet.filter)}`);
                }
            });

            parameters.push(`facets=${encodeURIComponent(fields.join(','))}`);
        }

        return `?${parameters.join('&')}`;
    }

    protected abstract path(): string;

}
