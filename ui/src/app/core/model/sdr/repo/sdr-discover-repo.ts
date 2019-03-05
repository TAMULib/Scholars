import { Observable } from 'rxjs';

import { SdrDiscoverRequest } from '../sdr-discover';
import { SolrDocument } from '../../discovery/solr-document';
import { SdrRepo } from './sdr-repo';

export interface SdrDiscoverRepo<D extends SolrDocument> extends SdrRepo<D> {

    search(request: SdrDiscoverRequest): Observable<D>;

}
