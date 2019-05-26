import { Injectable } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Location } from '@angular/common';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';

import { filter, map, withLatestFrom, skipWhile } from 'rxjs/operators';

import { AppState } from '../';

import { selectLoginRedirect } from '../auth';

import * as fromAuth from '../auth/auth.actions';
import * as fromDialog from '../dialog/dialog.actions';
import * as fromRouter from './router.actions';

@Injectable()
export class RouterEffects {

    constructor(
        private actions: Actions,
        private router: Router,
        private route: ActivatedRoute,
        private location: Location,
        private store: Store<AppState>
    ) {
        this.listenForRouteChange();
    }

    @Effect({ dispatch: false }) navigate = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.GO),
        map((action: fromRouter.Go) => action.payload),
        map(({ path, query: queryParams, extras }) => this.router.navigate(path, {
            relativeTo: this.route,
            queryParams,
            ...extras
        }))
    );

    @Effect({ dispatch: false }) navigateByUrl = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.LINK),
        map((action: fromRouter.Link) => action.payload),
        map(({ url }) => this.router.navigateByUrl(url))
    );

    @Effect({ dispatch: false }) navigateBack = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.BACK),
        map(() => this.location.back())
    );

    @Effect({ dispatch: false }) navigateForward = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.FORWARD),
        map(() => this.location.forward())
    );

    @Effect() redirect = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.CHANGED),
        withLatestFrom(this.store.pipe(select(selectLoginRedirect))),
        map(([action, redirect]) => redirect),
        skipWhile((redirect: fromRouter.RouterNavigation) => redirect === undefined),
        map(() => new fromAuth.UnsetLoginRedirectAction())
    );

    @Effect() closeDialog = this.actions.pipe(
        ofType(fromRouter.RouterActionTypes.CHANGED),
        map(() => new fromDialog.CloseDialogAction())
    );

    private listenForRouteChange() {
        this.router.events.pipe(
            filter(event => event instanceof NavigationEnd)
        ).subscribe(() => {
            this.store.dispatch(new fromRouter.Changed());
        });
    }

}
