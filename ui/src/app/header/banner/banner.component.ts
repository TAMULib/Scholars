import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from '../../core/store';
import { Banner } from '../../core/model/theme/banner';

import { selectActiveThemeHeaderBanner } from '../../core/store/themes';


@Component({
    selector: 'scholars-banner',
    templateUrl: 'banner.component.html',
    styleUrls: ['banner.component.scss']
})
export class BannerComponent implements OnInit {

    public banner: Observable<Banner>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.banner = this.store.pipe(
            select(selectActiveThemeHeaderBanner),
            skipWhile((banner: Banner) => banner === undefined)
        );
    }

}
