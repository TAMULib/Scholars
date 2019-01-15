import { Component, OnInit } from '@angular/core';

import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from '../core/store';
import { Footer } from '../core/model/theme/footer';
import { User, Role } from '../core/model/user';

import { LoginComponent } from '../shared/dialog/login/login.component';
import { RegistrationComponent, RegistrationStep } from '../shared/dialog/registration/registration.component';

import { selectIsAuthenticated, selectUser, selectHasRole } from '../core/store/auth';
import { selectActiveThemeFooter } from '../core/store/themes';

import * as fromAuth from '../core/store/auth/auth.actions';
import * as fromDialog from '../core/store/dialog/dialog.actions';

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

    constructor(private store: Store<AppState>) {

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
        this.store.dispatch(new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: LoginComponent,
                    inputs: {}
                },
                options: {
                    centered: false,
                    backdrop: 'static',
                    ariaLabelledBy: 'Login dialog'
                }
            }
        }));
    }

    public openRegistrationDialog(): void {
        this.store.dispatch(new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: RegistrationComponent,
                    inputs: {
                        step: RegistrationStep.SUBMIT
                    }
                },
                options: {
                    centered: false,
                    backdrop: 'static',
                    ariaLabelledBy: 'Registration dialog'
                }
            }
        }));
    }

    public logout(): void {
        this.store.dispatch(new fromAuth.LogoutAction());
    }

}
