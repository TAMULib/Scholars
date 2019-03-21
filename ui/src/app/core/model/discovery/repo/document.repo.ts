import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { Document } from '../document';

@Injectable({
    providedIn: 'root',
})
export class DocumentRepo extends AbstractSdrRepo<Document> {

    protected path(): string {
        return 'documents';
    }

}
