import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo';
import { ViewRepo } from './view.repo';

import { ResultView } from '../';

@Injectable({
    providedIn: 'root',
})
export class ResultViewRepo extends AbstractSdrRepo<ResultView> implements ViewRepo<ResultView> {

    protected path(): string {
        return 'resultViews';
    }

}
