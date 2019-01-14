import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';

import { AppState } from '../../core/store';

import * as fromRouter from '../../core/store/router/router.actions';

@Component({
    selector: 'scholars-stats-box',
    templateUrl: 'stats-box.component.html',
    styleUrls: ['stats-box.component.scss']
})
export class StatsBoxComponent {

    @Input() label: string;

    @Input() value: string;

    @Input() route: string;

    constructor(private store: Store<AppState>) {

    }

    public onNavigate(): void {
        if (this.route !== undefined) {
            this.store.dispatch(new fromRouter.Go({
                path: [this.route]
            }));
        }
    }

}
