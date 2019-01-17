import { SdrResource } from '../';
import { SdrPageRequest } from '../sdr-page';
import { Observable } from 'rxjs';
import { SdrCollection } from '../sdr-collection';

export interface SdrRepo<R extends SdrResource> {

    page(page: SdrPageRequest): Observable<SdrCollection>;

    getAll(): Observable<SdrCollection>;

    getOne(id: string | number): Observable<R>;

    post(resource: R): Observable<R>;

    put(resource: R): Observable<R>;

    patch(resource: R): Observable<R>;

    delete(resource: R): Observable<string>;

}
