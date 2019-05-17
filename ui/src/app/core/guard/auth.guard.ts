import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Inject, Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { Observable, of, scheduled } from 'rxjs';
import { asap } from 'rxjs/internal/scheduler/asap';
import { filter, map, switchMap } from 'rxjs/operators';

import { AlertService } from '../service/alert.service';
import { DialogService } from '../service/dialog.service';

import { AppState } from '../store';
import { Role, User } from '../model/user';

import { selectIsAuthenticated, selectUser } from '../store/auth';

import * as fromAuth from '../store/auth/auth.actions';
import * as fromRouter from '../store/router/router.actions';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private alert: AlertService,
        private dialog: DialogService,
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
        return roles ? scheduled([true], asap) : scheduled([false], asap);
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
                            this.store.dispatch(this.alert.unsubscribeFailureAlert());
                        }
                    }
                    return authorized;
                })
            ) : scheduled([false], asap))
        );
    }

    private isAuthenticated(url: string): Observable<boolean> {
        return this.store.pipe(
            select(selectIsAuthenticated),
            map((authenticated: boolean) => {
                if (!authenticated) {
                    this.store.dispatch(new fromRouter.Go({ path: ['/'] }));
                    if (isPlatformBrowser(this.platformId)) {
                        this.store.dispatch(new fromAuth.SetLoginRedirectAction({ navigation: { path: [url] } }));
                        this.store.dispatch(this.dialog.loginDialog());
                        this.store.dispatch(this.alert.forbiddenAlert());
                    }
                }
                return authenticated;
            })
        );
    }

}
