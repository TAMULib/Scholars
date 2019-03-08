import { Injectable, Injector } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';

import { of, combineLatest, defer } from 'rxjs';
import { map, switchMap, catchError, withLatestFrom, skipWhile, take } from 'rxjs/operators';

import { AlertService } from '../../service/alert.service';

import { AppState } from '../';
import { AbstractSdrRepo } from '../../model/sdr/repo/abstract-sdr-repo';
import { SdrResource, SdrCollection } from '../../model/sdr';

import { injectable, repos } from '../../model/repos';

import { selectIsStompConnected } from '../stomp';

import * as fromDialog from '../dialog/dialog.actions';
import * as fromStomp from '../stomp/stomp.actions';
import * as fromSdr from './sdr.actions';

@Injectable()
export class SdrEffects {

    private repos: Map<string, AbstractSdrRepo<SdrResource>>;

    constructor(
        private actions: Actions,
        private injector: Injector,
        private store: Store<AppState>,
        private alert: AlertService
    ) {
        this.repos = new Map<string, AbstractSdrRepo<SdrResource>>();
        this.injectRepos();
    }

    // TODO: alerts should be in dialog location if a dialog is opened

    @Effect() getAll = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ALL)),
        switchMap((action: fromSdr.GetAllResourcesAction) =>
            this.repos.get(action.name).getAll().pipe(
                map((collection: SdrCollection) => new fromSdr.GetAllResourcesSuccessAction(action.name, { collection })),
                catchError((response) => of(new fromSdr.GetAllResourcesFailureAction(action.name, { response })))
            )
        )
    );

    @Effect({ dispatch: false }) getAllSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ALL_SUCCESS)),
        switchMap((action: fromSdr.GetAllResourcesSuccessAction) => combineLatest(
            of(action.name),
            this.store.pipe(
                select(selectIsStompConnected),
                skipWhile((connected: boolean) => !connected),
                take(1)
            )
        )),
        withLatestFrom(this.store),
        map(([combination, store]) => {
            const name = combination[0];
            if (!store.stomp.subscriptions.has(`/queue/${name}`)) {
                this.store.dispatch(new fromStomp.SubscribeAction({
                    channel: `/queue/${name}`,
                    handle: (frame: any) => {
                        // TODO: conditionally reload all
                        if (frame.command === 'MESSAGE') {
                            console.log(frame);
                        }
                    }
                }));
            }
        })
    );

    @Effect() getAllFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.GET_ALL_FAILURE)),
        map((action: fromSdr.GetAllResourcesFailureAction) => this.alert.getAllFailureAlert(action.payload))
    );

    @Effect() page = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE)),
        switchMap((action: fromSdr.PageResourcesAction) =>
            this.repos.get(action.name).page(action.payload.request).pipe(
                map((collection: SdrCollection) => new fromSdr.PageResourcesSuccessAction(action.name, { collection })),
                catchError((response) => of(new fromSdr.PageResourcesFailureAction(action.name, { response })))
            )
        )
    );

    @Effect({ dispatch: false }) pageSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE_SUCCESS)),
        switchMap((action: fromSdr.PageResourcesSuccessAction) => combineLatest(
            of(action.name),
            this.store.pipe(
                select(selectIsStompConnected),
                skipWhile((connected: boolean) => !connected),
                take(1)
            )
        )),
        withLatestFrom(this.store),
        map(([combination, store]) => {
            const name = combination[0];
            if (!store.stomp.subscriptions.has(`/queue/${name}`)) {
                this.store.dispatch(new fromStomp.SubscribeAction({
                    channel: `/queue/${name}`,
                    handle: (frame: any) => {
                        // TODO: conditionally reload page
                        if (frame.command === 'MESSAGE') {
                            console.log(frame);
                        }
                    }
                }));
            }
        })
    );

    @Effect() pageFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE_FAILURE)),
        map((action: fromSdr.PageResourcesFailureAction) => this.alert.pageFailureAlert(action.payload))
    );

    @Effect() search = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.SEARCH)),
        switchMap((action: fromSdr.SearchResourcesAction) =>
            this.repos.get(action.name).search(action.payload.request).pipe(
                map((collection: SdrCollection) => new fromSdr.SearchResourcesSuccessAction(action.name, { collection })),
                catchError((response) => of(new fromSdr.SearchResourcesFailureAction(action.name, { response })))
            )
        )
    );

    @Effect({ dispatch: false }) searchSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.SEARCH_SUCCESS)),
        switchMap((action: fromSdr.SearchResourcesSuccessAction) => combineLatest(
            of(action.name),
            this.store.pipe(
                select(selectIsStompConnected),
                skipWhile((connected: boolean) => !connected),
                take(1)
            )
        )),
        withLatestFrom(this.store),
        map(([combination, store]) => {
            const name = combination[0];
            if (!store.stomp.subscriptions.has(`/queue/${name}`)) {
                this.store.dispatch(new fromStomp.SubscribeAction({
                    channel: `/queue/${name}`,
                    handle: (frame: any) => {
                        // TODO: conditionally reload search
                        if (frame.command === 'MESSAGE') {
                            console.log(frame);
                        }
                    }
                }));
            }
        })
    );

    @Effect() searchFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.SEARCH_FAILURE)),
        map((action: fromSdr.SearchResourcesFailureAction) => this.alert.searchFailureAlert(action.payload))
    );

    @Effect() clearResourceSubscription = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.CLEAR)),
        map((action: fromSdr.PageResourcesSuccessAction) => new fromStomp.UnsubscribeAction({ channel: `/queue/${action.name}` }))
    );

    @Effect() post = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST)),
        switchMap((action: fromSdr.PostResourceAction) =>
            this.repos.get(action.name).post(action.payload.resource).pipe(
                map((resource: SdrResource) => new fromSdr.PostResourceSuccessAction(action.name, { resource })),
                catchError((response) => of(new fromSdr.PostResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() postSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST_SUCCESS)),
        switchMap((action: fromSdr.PostResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.postSuccessAlert(action)
        ])
    );

    @Effect() postFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST_FAILURE)),
        map((action: fromSdr.PostResourceFailureAction) => this.alert.postFailureAlert(action.payload))
    );

    @Effect() put = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT)),
        switchMap((action: fromSdr.PutResourceAction) =>
            this.repos.get(action.name).put(action.payload.resource).pipe(
                map((resource: SdrResource) => new fromSdr.PutResourceSuccessAction(action.name, { resource })),
                catchError((response) => of(new fromSdr.PutResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() putSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT_SUCCESS)),
        switchMap((action: fromSdr.PutResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.putSuccessAlert(action)
        ])
    );

    @Effect() putFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT_FAILURE)),
        map((action: fromSdr.PutResourceFailureAction) => this.alert.putFailureAlert(action.payload))
    );

    @Effect() patch = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH)),
        switchMap((action: fromSdr.PatchResourceAction) =>
            this.repos.get(action.name).patch(action.payload.resource).pipe(
                map((resource: SdrResource) => new fromSdr.PatchResourceSuccessAction(action.name, { resource })),
                catchError((response) => of(new fromSdr.PatchResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() patchSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH_SUCCESS)),
        switchMap((action: fromSdr.PatchResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.patchSuccessAlert(action)
        ])
    );

    @Effect() patchFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH_FAILURE)),
        map((action: fromSdr.PatchResourceFailureAction) => this.alert.patchFailureAlert(action.payload))
    );

    @Effect() delete = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE)),
        switchMap((action: fromSdr.DeleteResourceAction) =>
            this.repos.get(action.name).delete(action.payload.id).pipe(
                map(() => new fromSdr.DeleteResourceSuccessAction(action.name)),
                catchError((response) => of(new fromSdr.DeleteResourceFailureAction(action.name, { response })))
            )
        )
    );

    @Effect() deleteSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE_SUCCESS)),
        switchMap((action: fromSdr.DeleteResourceSuccessAction) => [
            new fromDialog.CloseDialogAction(),
            this.alert.deleteSuccessAlert(action)
        ])
    );

    @Effect() deleteFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE_FAILURE)),
        map((action: fromSdr.DeleteResourceFailureAction) => this.alert.deleteFailureAlert(action.payload))
    );

    @Effect() initDirectoryViews = defer(() => {
        return of(new fromSdr.GetAllResourcesAction('directoryViews'));
    });

    private buildActions(actionType: fromSdr.SdrActionTypes): string[] {
        const loadActions = [];
        for (const name in repos) {
            if (repos.hasOwnProperty(name)) {
                loadActions.push(fromSdr.getSdrAction(actionType, name));
            }
        }
        return loadActions;
    }

    private injectRepos(): void {
        const injector = Injector.create({
            providers: injectable,
            parent: this.injector
        });
        for (const name in repos) {
            if (repos.hasOwnProperty(name)) {
                this.repos.set(name, injector.get<AbstractSdrRepo<SdrResource>>(repos[name]));
            }
        }
    }

}
