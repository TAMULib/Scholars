import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo/abstract-sdr-repo';
import { Theme } from '../theme';
import { SdrCollection } from '../../sdr';
import { SdrRequest } from '../../request';

@Injectable({
    providedIn: 'root',
})
export class ThemeRepo extends AbstractSdrRepo<Theme> {

    protected path(): string {
        return 'themes';
    }

    public search(request: SdrRequest): Observable<SdrCollection> {
        throw new Error('Themes does not support faceted search!');
    }

}
