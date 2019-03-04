import { Injectable } from '@angular/core';

import { AbstractSdrDiscoverRepo } from '../../sdr/repo/abstract-sdr-discover-repo';
import { Document } from '../document';

@Injectable({
    providedIn: 'root',
})
export class DocumentRepo extends AbstractSdrDiscoverRepo<Document> {

    protected path(): string {
        return 'documents';
    }

}
