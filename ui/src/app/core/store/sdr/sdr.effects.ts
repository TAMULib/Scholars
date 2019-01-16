import { Injectable, Injector } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';

import { of, timer } from 'rxjs';
import { map, switchMap, catchError, withLatestFrom, skipUntil, last, skipWhile, takeUntil, distinctUntilChanged, debounce } from 'rxjs/operators';

import { AppState } from '..';
import { AlertLocation, AlertType } from '../alert/alert.model';
import { AbstractSdrRepo } from '../../model/sdr/repo/abstract-sdr-repo';
import { SdrResource, SdrCollection } from '../../model/sdr';

import { injectable, repos } from '../../model/repos';

import { selectIsDisconnected } from '../stomp';

import * as fromAlerts from '../alert/alert.actions';
import * as fromDialog from '../dialog/dialog.actions';
import * as fromStomp from '../stomp/stomp.actions';
import * as fromSdr from './sdr.actions';

@Injectable()
export class SdrEffects {

    private repos: Map<string, AbstractSdrRepo<SdrResource>>;

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private injector: Injector
    ) {
        this.repos = new Map<string, AbstractSdrRepo<SdrResource>>();
        this.injectRepos();
    }

    // TODO: alerts should be in dialog location if a dialog is opened

    @Effect() page = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE)),
        switchMap((action: fromSdr.PageResourcesAction) =>
            this.repos.get(action.name).page(action.payload.page).pipe(
                map((collection: SdrCollection) => new fromSdr.PageResourcesSuccessAction(action.name, { collection })),
                catchError((response) => of(new fromSdr.PageResourcesFailureAction(action.name, { response })))
            )
        )
    );

    @Effect({ dispatch: false }) pageSuccess = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE_SUCCESS)),
        map((action: fromSdr.PageResourcesSuccessAction) => action),
        withLatestFrom(this.store),
        debounce(([action, store]) => timer(store.stomp.connected ? 0 : 1000)),
        map(([action, store]) => {
            if (!store.stomp.subscriptions.has(`/queue/${action.name}`)) {
                console.log('subscribe');
                this.store.dispatch(new fromStomp.SubscribeAction({
                    channel: `/queue/${action.name}`,
                    handle: (message: any) => {
                        // TODO: prevent page refresh if updating or loading
                        console.log(message);
                        this.store.dispatch(new fromSdr.PageResourcesAction(action.name, { page: store[action.name].page }));
                    }
                }));
            } else {
                console.log('already subscribed');
            }
        })
    );

    @Effect() pageFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PAGE_FAILURE)),
        map((action: fromSdr.PageResourcesFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
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
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: `Successfully posted ${action.name}.`,
                    dismissible: true,
                    timer: 10000
                }
            })
        ])
    );

    @Effect() postFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.POST_FAILURE)),
        map((action: fromSdr.PostResourceFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
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
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: `Successfully put ${action.name}.`,
                    dismissible: true,
                    timer: 10000
                }
            })
        ])
    );

    @Effect() putFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PUT_FAILURE)),
        map((action: fromSdr.PutResourceFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
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
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: `Successfully patched ${action.name}.`,
                    dismissible: true,
                    timer: 10000
                }
            })
        ])
    );

    @Effect() patchFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.PATCH_FAILURE)),
        map((action: fromSdr.PatchResourceFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
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
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: `Successfully deleted ${action.name}.`,
                    dismissible: true,
                    timer: 10000
                }
            })
        ])
    );

    @Effect() deleteFailure = this.actions.pipe(
        ofType(...this.buildActions(fromSdr.SdrActionTypes.DELETE_FAILURE)),
        map((action: fromSdr.DeleteResourceFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
    );

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
