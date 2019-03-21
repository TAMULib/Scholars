import { Observable } from 'rxjs';

import { SdrRequest } from '../../request';
import { SdrResource } from '../sdr-resource';
import { SdrCollection } from '../sdr-collection';

export interface SdrRepo<R extends SdrResource> {

    search(request: SdrRequest): Observable<SdrCollection>;

    page(request: SdrRequest): Observable<SdrCollection>;

    getAll(): Observable<SdrCollection>;

    getOne(id: string | number): Observable<R>;

    post(resource: R): Observable<R>;

    put(resource: R): Observable<R>;

    patch(resource: R): Observable<R>;

    delete(resource: R): Observable<string>;

}
