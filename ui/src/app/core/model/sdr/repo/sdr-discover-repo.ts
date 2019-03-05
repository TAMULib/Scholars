import { Observable } from 'rxjs';

import { SdrCollection } from '../sdr-collection';
import { SdrDiscoverRequest, SdrDiscoverFilter } from '../sdr-discover';
import { SdrRepo } from './sdr-repo';
import { SdrResource } from '../';

export interface SdrDiscoverRepo<R extends SdrResource> extends SdrRepo<R> {

    search(request: SdrDiscoverRequest): Observable<R>;

}
