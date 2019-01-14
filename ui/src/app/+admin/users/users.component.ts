import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';
import { SdrPage, SdrPageRequest } from '../../core/model/sdr';
import { User, Role } from '../../core/model/user';

import { selectAllUsers, selectUsersPage } from '../../core/store/users';

import * as fromUsers from '../../core/store/users/users.actions';

@Component({
    selector: 'scholars-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class UsersComponent implements OnInit {

    public users: Observable<User[]>;

    public page: Observable<SdrPage>;

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.users = this.store.pipe(select(selectAllUsers));
        this.page = this.store.pipe(select(selectUsersPage));
    }

    public getRoleValue(role: Role): string {
        return Role[role];
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromUsers.LoadUsersAction({ page }));
    }

}
