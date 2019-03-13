import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable, BehaviorSubject } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../../core/store';
import { DiscoveryView } from '../../core/model/view';

import { selectDefaultDiscoveryView } from '../../core/store/sdr';

@Component({
    selector: 'scholars-about',
    templateUrl: 'about.component.html',
    styleUrls: ['about.component.scss']
})
export class AboutComponent implements OnInit {

    public loading: BehaviorSubject<boolean>;

    public anotherLoading: BehaviorSubject<boolean>;

    public discoveryView: Observable<DiscoveryView>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.loading = new BehaviorSubject<boolean>(false);
        this.anotherLoading = new BehaviorSubject<boolean>(false);

        setTimeout(() => {
            this.loading.next(true);
        }, 1000);

        setTimeout(() => {
            this.loading.next(false);
        }, 5000);

        setTimeout(() => {
            this.anotherLoading.next(true);
        }, 7500);

        setTimeout(() => {
            this.anotherLoading.next(false);
        }, 10000);

        this.discoveryView = this.store.pipe(
            select(selectDefaultDiscoveryView),
            filter((view: DiscoveryView) => view !== undefined)
        );
    }

    public isLoading(): Observable<boolean> {
        return this.loading.asObservable();
    }

    public isAnotherLoading(): Observable<boolean> {
        return this.anotherLoading.asObservable();
    }

}
