import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { DialogService } from '../../core/service/dialog.service';

import { AppState } from '../../core/store';
import { SdrPage, SdrPageRequest } from '../../core/model/sdr';
import { User, Role } from '../../core/model/user';

import { selectAllResources, selectResourcesPage } from '../../core/store/sdr';

import * as fromSdr from '../../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class UsersComponent implements OnInit {

    public users: Observable<User[]>;

    public page: Observable<SdrPage>;

    constructor(
        private store: Store<AppState>,
        private dialog: DialogService
    ) {

    }

    ngOnInit() {
        this.users = this.store.pipe(select(selectAllResources<User>('users')));
        this.page = this.store.pipe(select(selectResourcesPage<User>('users')));
    }

    public openUserEditDialog(user: User): void {
        this.store.dispatch(this.dialog.userEditDialog(user));
    }

    public getRoleValue(role: Role): string {
        return Role[role];
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromSdr.PageResourcesAction('users', { page }));
    }

}
