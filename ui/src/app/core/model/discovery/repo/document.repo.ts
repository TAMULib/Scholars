import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Document } from '../document';

@Injectable({
    providedIn: 'root',
})
export class DocumentRepo extends AbstractSdrRepo<Document> {

    protected path(): string {
        return 'documents';
    }

    public post(document: Document): Observable<Document> {
        throw new Error('Documents does not support post!');
    }

    public put(document: Document): Observable<Document> {
        throw new Error('Documents does not support put!');
    }

    public patch(document: Document): Observable<Document> {
        throw new Error('Documents does not support patch!');
    }

    public delete(document: Document): Observable<string> {
        throw new Error('Documents does not support delete!');
    }

    public findByTypesIn(types: string[]): Observable<Document> {
        throw new Error('Documents does not support find by types in!');
    }

}
