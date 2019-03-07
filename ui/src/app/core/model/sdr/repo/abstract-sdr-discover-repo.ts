import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from './abstract-sdr-repo';
import { SdrDiscoverRepo } from './sdr-discover-repo';

import { SolrDocument } from '../../discovery/solr-document';
import { SdrDiscoverRequest } from '../sdr-discover';

import { environment } from '../../../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export abstract class AbstractSdrDiscoverRepo<D extends SolrDocument> extends AbstractSdrRepo<D> implements SdrDiscoverRepo<D> {

    public search(request: SdrDiscoverRequest): Observable<D> {
        const parameters: String[] = [];

        if (request.query) {
            parameters.push(`query=${encodeURIComponent(request.query)}`);
        }

        if (request.type.filter) {
            parameters.push(`type.filter=${encodeURIComponent(request.type.filter)}`);
        }

        if (request.type) {
            for (const key in ['limit', 'offset', 'sort']) {
                if (request.type[key]) {
                    parameters.push(`type.limit=${request.type[key]}`);
                }
            }
        }

        if (request.facets) {
            parameters.push(`facets=${encodeURIComponent(request.facets.join())}`);
        }

        return this.restService.get<D>(`${environment.service}/${this.path()}/search/facet${this.mapParameters(request, parameters)}`, {
            withCredentials: true
        });
    }

}
