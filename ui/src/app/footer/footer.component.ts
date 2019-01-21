import { Component, OnInit } from '@angular/core';

import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { DialogService } from '../core/service/dialog.service';

import { AppState } from '../core/store';
import { Footer } from '../core/model/theme/footer';
import { User, Role } from '../core/model/user';

import { RegistrationStep } from '../shared/dialog/registration/registration.component';

import { selectIsAuthenticated, selectUser, selectHasRole } from '../core/store/auth';
import { selectActiveThemeFooter } from '../core/store/theme';

import * as fromAuth from '../core/store/auth/auth.actions';

@Component({
    selector: 'scholars-footer',
    templateUrl: 'footer.component.html',
    styleUrls: ['footer.component.scss']
})
export class FooterComponent implements OnInit {

    public isAuthenticated: Observable<boolean>;

    public isAdmin: Observable<boolean>;

    public user: Observable<User>;

    public footer: Observable<Footer>;

    constructor(
        private store: Store<AppState>,
        private dialog: DialogService
    ) {

    }

    ngOnInit() {
        this.isAuthenticated = this.store.pipe(select(selectIsAuthenticated));
        this.isAdmin = this.store.pipe(select(selectHasRole(Role.ROLE_ADMIN)));
        this.user = this.store.pipe(select(selectUser));
        this.footer = this.store.pipe(
            select(selectActiveThemeFooter),
            skipWhile((footer: Footer) => footer === undefined)
        );
    }

    public openLoginDialog(): void {
        this.store.dispatch(this.dialog.loginDialog());
    }

    public openRegistrationDialog(): void {
        this.store.dispatch(this.dialog.registrationDialog(RegistrationStep.SUBMIT, {
            firstName: '',
            lastName: '',
            email: ''
        }));
    }

    public logout(): void {
        this.store.dispatch(new fromAuth.LogoutAction());
    }

}
