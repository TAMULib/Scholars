import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Inject, Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { Observable, of } from 'rxjs';
import { filter, map, switchMap } from 'rxjs/operators';

import { AppState } from '../store';
import { AlertLocation, AlertType } from '../store/alert/alert.model';
import { Role, User } from '../model/user';

import { LoginComponent } from '../../shared/dialog/login/login.component';

import { selectIsAuthenticated, selectUser } from '../store/auth';

import * as fromAlerts from '../store/alert/alert.actions';
import * as fromAuth from '../store/auth/auth.actions';
import * as fromDialog from '../store/dialog/dialog.actions';
import * as fromRouter from '../store/router/router.actions';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private store: Store<AppState>
    ) {

    }

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        const roles = route.data.roles;
        return this.requiresAuthorization(roles).pipe(
            switchMap((authorize: boolean) => {
                return authorize ? this.isAuthorized(state.url, roles) : this.isAuthenticated(state.url);
            })
        );
    }

    private requiresAuthorization(roles: Role[]): Observable<boolean> {
        return roles ? of(true) : of(false);
    }

    private isAuthorized(url: string, roles: Role[]): Observable<boolean> {
        return this.isAuthenticated(url).pipe(
            switchMap((authenticated: boolean) => authenticated ? this.store.pipe(
                select(selectUser),
                filter((user: User) => user !== undefined),
                map((user: User) => {
                    const authorized = user ? roles.indexOf(Role[user.role]) >= 0 : false;
                    if (!authorized) {
                        this.store.dispatch(new fromRouter.Go({ path: ['/'] }));
                        if (isPlatformBrowser(this.platformId)) {
                            this.store.dispatch(new fromAlerts.AddAlertAction({
                                alert: {
                                    location: AlertLocation.MAIN,
                                    type: AlertType.DANGER,
                                    message: 'Unauthorized!',
                                    dismissible: true,
                                    timer: 10000
                                }
                            }));
                        }
                    }
                    return authorized;
                })
            ) : of(false))
        );
    }

    private isAuthenticated(url: string): Observable<boolean> {
        return this.store.pipe(
            select(selectIsAuthenticated),
            map((authenticated: boolean) => {
                if (!authenticated) {
                    this.store.dispatch(new fromRouter.Go({ path: ['/'] }));
                    if (isPlatformBrowser(this.platformId)) {
                        this.store.dispatch(new fromAuth.SetLoginRedirectAction({ path: [url] }));
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
                        this.store.dispatch(new fromAlerts.AddAlertAction({
                            alert: {
                                location: AlertLocation.DIALOG,
                                type: AlertType.WARNING,
                                message: 'Forbidden! You must be logged in to access.',
                                dismissible: false
                            }
                        }));
                    }
                }
                return authenticated;
            })
        );
    }

}
