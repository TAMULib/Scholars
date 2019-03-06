import { Component, Input, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { Alert, AlertLocation } from '../../core/model/alert';

import { selectAlertsByLocation } from '../../core/store/alert';

import * as fromAlert from '../../core/store/alert/alert.actions';

@Component({
    selector: 'scholars-alert',
    templateUrl: './alert.component.html',
    styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {

    @Input() location: AlertLocation;

    public alerts: Observable<Alert[]>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.alerts = this.store.pipe(select(selectAlertsByLocation(this.location)));
    }

    public close(alert: Alert): void {
        this.store.dispatch(new fromAlert.DismissAlertAction({ alert }));
    }

    public isMain(): boolean {
        return this.location === AlertLocation.MAIN;
    }

}
