import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { UserEditComponent } from '../../shared/dialog/user-edit/user-edit.component';

import { AppState } from '../../core/store';
import { SdrPage, SdrPageRequest } from '../../core/model/sdr';
import { User, Role } from '../../core/model/user';

import { selectAllResources, selectReousrcesPage } from '../../core/store/sdr';

import * as fromDialog from '../../core/store/dialog/dialog.actions';
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

    constructor(private store: Store<AppState>) {

    }

    ngOnInit() {
        this.users = this.store.pipe(select(selectAllResources<User>('users')));
        this.page = this.store.pipe(select(selectReousrcesPage<User>('users')));
    }

    public openUserEditDialog(user: User): void {
        this.store.dispatch(new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: UserEditComponent,
                    inputs: { user }
                },
                options: {
                    centered: false,
                    backdrop: 'static',
                    ariaLabelledBy: 'User edit dialog'
                }
            }
        }));
    }

    public getRoleValue(role: Role): string {
        return Role[role];
    }

    public onPageChange(page: SdrPageRequest): void {
        this.store.dispatch(new fromSdr.PageResourcesAction('users', { page }));
    }

}
