import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SdrPage } from '../../core/model/sdr';
import { DiscoveryView } from '../../core/model/view';

import { selectAllResources, selectResourcesPage } from '../../core/store/sdr';

@Component({
    selector: 'scholars-discovery-views',
    templateUrl: './discovery-views.component.html',
    styleUrls: ['./discovery-views.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class DiscoveryViewsComponent implements OnInit {

    public discoveryViews: Observable<DiscoveryView[]>;

    public page: Observable<SdrPage>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.discoveryViews = this.store.pipe(select(selectAllResources<DiscoveryView>('discoveryViews')));
        this.page = this.store.pipe(select(selectResourcesPage<DiscoveryView>('discoveryViews')));
    }

}
