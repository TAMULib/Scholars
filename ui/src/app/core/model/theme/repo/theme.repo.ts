import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { AbstractSdrRepo } from '../../sdr/repo/abstract-sdr-repo';
import { Theme } from '../theme';
import { SdrCollection, Count } from '../../sdr';
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

    public count(request: SdrRequest): Observable<Count> {
        throw new Error('Themes does not support count!');
    }

    public findByTypesIn(types: string[]): Observable<Theme> {
        throw new Error('Themes does not support find by types in!');
    }

}
