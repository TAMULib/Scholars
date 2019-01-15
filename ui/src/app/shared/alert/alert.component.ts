import { Component, HostBinding, Input, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { Alert, AlertLocation } from '../../core/store/alert/alert.model';

import { selectAlertsByLocation } from '../../core/store/alert';

import * as fromAlerts from '../../core/store/alert/alert.actions';

@Component({
    selector: 'scholars-alert',
    templateUrl: './alert.component.html',
    styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {

    @HostBinding('style.position') position;

    @HostBinding('style.display') display;

    @HostBinding('style.width') width;

    @HostBinding('style.top') top;

    @HostBinding('style.z-index') zIndex;

    @Input() location: AlertLocation;

    public alerts: Observable<Alert[]>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.alerts = this.store.pipe(select(selectAlertsByLocation(this.location)));
        if (this.isMain()) {
            this.position = 'fixed';
            this.display = 'flex';
            this.width = '100%';
            this.top = '7.5%';
            this.zIndex = 1;
        }
    }

    public close(alert: Alert): void {
        this.store.dispatch(new fromAlerts.DismissAlertAction({ alert }));
    }

    public isMain(): boolean {
        return this.location === AlertLocation.MAIN;
    }

}
