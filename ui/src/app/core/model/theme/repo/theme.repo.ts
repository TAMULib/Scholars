import { Injectable } from '@angular/core';

import { AbstractSdrRepo } from '../../sdr/repo/abstract-sdr-repo';
import { Theme } from '../theme';

@Injectable({
    providedIn: 'root',
})
export class ThemeRepo extends AbstractSdrRepo<Theme> {

    protected path(): string {
        return 'themes';
    }

}
