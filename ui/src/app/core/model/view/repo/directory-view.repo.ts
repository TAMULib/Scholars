import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { ViewRepo } from './view.repo';

import { DirectoryView } from '../';

@Injectable({
    providedIn: 'root',
})
export class DirectoryViewRepo extends AbstractSdrRepo<DirectoryView> implements ViewRepo<DirectoryView> {

    protected path(): string {
        return 'directoryViews';
    }

}
