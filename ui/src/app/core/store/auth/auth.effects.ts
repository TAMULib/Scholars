import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';

import { defer, of, timer } from 'rxjs';
import { catchError, map, switchMap, withLatestFrom, debounce } from 'rxjs/operators';

import { AppState } from '../';

import { AlertType, AlertLocation } from '../alert';
import { User, Role } from '../../model/user';
import { LoginRequest, RegistrationRequest } from '../../model/request';

import { NotificationComponent } from '../../../shared/dialog/notification/notification.component';
import { RegistrationComponent, RegistrationStep } from '../../../shared/dialog/registration/registration.component';

import { AuthService } from '../../service/auth.service';

import { selectLoginRedirect } from './';

import * as fromAuth from './auth.actions';
import * as fromDialog from '../dialog/dialog.actions';
import * as fromAlerts from '../alert/alert.actions';
import * as fromRouter from '../router/router.actions';
import * as fromStomp from '../stomp/stomp.actions';
import * as fromSdr from '../sdr/sdr.actions';

@Injectable()
export class AuthEffects {

    constructor(
        private actions: Actions,
        private store: Store<AppState>,
        private authService: AuthService
    ) {

    }

    @Effect() login = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGIN),
        map((action: fromAuth.LoginAction) => action.payload),
        switchMap((payload: { login: LoginRequest }) =>
            this.authService.login(payload.login).pipe(
                map((user: User) => new fromAuth.LoginSuccessAction({ user })),
                catchError((response) => of(new fromAuth.LoginFailureAction({ response })))
            )
        )
    );

    @Effect() loginSuccess = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGIN_SUCCESS),
        map((action: fromAuth.LoginSuccessAction) => action.payload),
        withLatestFrom(this.store.select(selectLoginRedirect)),
        switchMap(([action, redirect]) => {
            const actions: any = [
                new fromDialog.CloseDialogAction(),
                new fromAlerts.AddAlertAction({
                    alert: {
                        location: AlertLocation.MAIN,
                        type: AlertType.SUCCESS,
                        message: 'Login success.',
                        dismissible: true,
                        timer: 10000
                    }
                })
            ];
            if (redirect !== undefined) {
                actions.push(new fromRouter.Go(redirect));
            }
            return actions;
        })
    );

    @Effect() loginFailure = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGIN_FAILURE),
        map((action: fromAuth.LoginFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.DIALOG,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 10000
            }
        }))
    );

    @Effect() submitRegistration = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.SUBMIT_REGISTRATION),
        map((action: fromAuth.SubmitRegistrationAction) => action.payload),
        switchMap((payload: { registration: RegistrationRequest }) =>
            this.authService.submitRegistration(payload.registration).pipe(
                map((registration: RegistrationRequest) => new fromAuth.SubmitRegistrationSuccessAction({ registration })),
                catchError((response) => of(new fromAuth.SubmitRegistrationFailureAction({ response })))
            )
        )
    );

    @Effect() submitRegistrationSuccess = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.SUBMIT_REGISTRATION_SUCCESS),
        map((action: fromAuth.SubmitRegistrationSuccessAction) => action.payload),
        map((payload: { registration: RegistrationRequest }) => payload.registration),
        switchMap(() => [
            new fromDialog.CloseDialogAction(),
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: `Confirm email to complete registration.`,
                    dismissible: true,
                    timer: 15000
                }
            })
        ])
    );

    @Effect() submitRegistrationFailure = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.SUBMIT_REGISTRATION_FAILURE),
        map((action: fromAuth.SubmitRegistrationFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.DIALOG,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 10000
            }
        }))
    );

    @Effect() confirmRegistration = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.CONFIRM_REGISTRATION),
        map((action: fromAuth.ConfirmRegistrationAction) => action.payload),
        switchMap((payload: { key: string }) =>
            this.authService.confirmRegistration(payload.key).pipe(
                map((registration: RegistrationRequest) => new fromAuth.ConfirmRegistrationSuccessAction({ registration })),
                catchError((response) => of(new fromAuth.ConfirmRegistrationFailureAction({ response })))
            )
        )
    );

    @Effect() confirmRegistrationSuccess = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.CONFIRM_REGISTRATION_SUCCESS),
        map((action: fromAuth.ConfirmRegistrationSuccessAction) => action.payload),
        map((payload: { registration: RegistrationRequest }) => payload.registration),
        switchMap((registration: RegistrationRequest) => {
            setTimeout(
                () => this.store.dispatch(
                    new fromAlerts.AddAlertAction({
                        alert: {
                            location: AlertLocation.DIALOG,
                            type: AlertType.SUCCESS,
                            message: `Set password to complete registration.`,
                            dismissible: false
                        }
                    })
                )
            );
            return [
                new fromDialog.CloseDialogAction(),
                new fromRouter.Go({ path: ['/'] }),
                new fromDialog.OpenDialogAction({
                    dialog: {
                        ref: {
                            component: RegistrationComponent,
                            inputs: {
                                step: RegistrationStep.COMPLETE,
                                registration
                            }
                        },
                        options: {
                            centered: false,
                            backdrop: 'static',
                            ariaLabelledBy: 'Complete registration dialog'
                        }
                    }
                })];

        })
    );

    @Effect() confirmRegistrationFailure = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.CONFIRM_REGISTRATION_FAILURE),
        map((action: fromAuth.ConfirmRegistrationFailureAction) => action.payload),
        switchMap((payload: { response: any }) => [
            new fromRouter.Go({ path: ['/'] }),
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.DANGER,
                    message: payload.response.error,
                    dismissible: true,
                    timer: 10000
                }
            })
        ])
    );

    @Effect() completeRegistration = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.COMPLETE_REGISTRATION),
        map((action: fromAuth.CompleteRegistrationAction) => action.payload),
        switchMap((payload: { registration: RegistrationRequest }) =>
            this.authService.completeRegistration(payload.registration).pipe(
                map((user: User) => new fromAuth.CompleteRegistrationSuccessAction({ user })),
                catchError((response) => of(new fromAuth.CompleteRegistrationFailureAction({ response })))
            )
        )
    );

    @Effect() completeRegistrationSuccess = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.COMPLETE_REGISTRATION_SUCCESS),
        map((action: fromAuth.CompleteRegistrationSuccessAction) => action.payload),
        map((payload: { user: User }) => payload.user),
        switchMap((user: User) => [
            new fromDialog.CloseDialogAction(),
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: `Registration complete. You can now login.`,
                    dismissible: true,
                    timer: 15000
                }
            })
        ])
    );

    @Effect() completeRegistrationFailure = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.COMPLETE_REGISTRATION_FAILURE),
        map((action: fromAuth.CompleteRegistrationFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.DIALOG,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 10000
            }
        }))
    );

    @Effect() logout = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGOUT),
        switchMap(() =>
            this.authService.logout().pipe(
                map((response: any) => new fromAuth.LogoutSuccessAction({ message: response.message })),
                catchError((response) => of(new fromAuth.LogoutFailureAction({ response })))
            )
        )
    );

    @Effect() logoutSuccess = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGOUT_SUCCESS),
        switchMap(() => [
            new fromSdr.ClearResourcesAction('Theme'),
            new fromSdr.ClearResourcesAction('User'),
            new fromRouter.Go({ path: ['/'] })
        ])
    );

    @Effect() getUser = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.GET_USER),
        switchMap(() =>
            this.authService.getUser().pipe(
                map((user: User) => new fromAuth.GetUserSuccessAction({ user })),
                catchError((response) => of(new fromAuth.GetUserFailureAction({ response })))
            )
        )
    );

    @Effect() getUserSuccess = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.GET_USER_SUCCESS),
        withLatestFrom(this.store),
        debounce(([action, store]) => timer(store.stomp.connected ? 0 : 2500)),
        map(([action, store]) => new fromStomp.SubscribeAction({
            channel: `/user/queue/users`,
            handle: (frame: any) => {
                if (frame.command === 'MESSAGE') {
                    const body = JSON.parse(frame.body);
                    switch (body.action) {
                        case 'DELETE':
                            this.store.dispatch(new fromAuth.LogoutAction());
                            this.store.dispatch(new fromDialog.OpenDialogAction({
                                dialog: {
                                    ref: {
                                        component: NotificationComponent,
                                        inputs: {
                                            text: 'Your account has been deleted!'
                                        }
                                    },
                                    options: {
                                        centered: false,
                                        backdrop: 'static',
                                        ariaLabelledBy: 'Notification dialog'
                                    }
                                }
                            }));
                            break;
                        case 'UPDATE':
                            if (!body.entity.enabled) {
                                this.store.dispatch(new fromAuth.LogoutAction());
                                this.store.dispatch(new fromDialog.OpenDialogAction({
                                    dialog: {
                                        ref: {
                                            component: NotificationComponent,
                                            inputs: {
                                                text: 'Your account has been disabled!'
                                            }
                                        },
                                        options: {
                                            centered: false,
                                            backdrop: 'static',
                                            ariaLabelledBy: 'Notification dialog'
                                        }
                                    }
                                }));
                            } else {
                                this.store.dispatch(new fromAuth.GetUserSuccessAction({ user: body.entity }));
                                const roles = Object.keys(Role);
                                if (roles.indexOf(body.entity.role) < roles.indexOf(store.auth.user.role)) {
                                    this.store.dispatch(new fromRouter.Go({ path: ['/'] }));
                                    this.store.dispatch(new fromDialog.OpenDialogAction({
                                        dialog: {
                                            ref: {
                                                component: NotificationComponent,
                                                inputs: {
                                                    text: 'Your permissions have been reduced!'
                                                }
                                            },
                                            options: {
                                                centered: false,
                                                backdrop: 'static',
                                                ariaLabelledBy: 'Notification dialog'
                                            }
                                        }
                                    }));
                                } else if (roles.indexOf(body.entity.role) > roles.indexOf(store.auth.user.role)) {
                                    this.store.dispatch(new fromDialog.OpenDialogAction({
                                        dialog: {
                                            ref: {
                                                component: NotificationComponent,
                                                inputs: {
                                                    text: 'Your permissions have been elevated!'
                                                }
                                            },
                                            options: {
                                                centered: false,
                                                backdrop: 'static',
                                                ariaLabelledBy: 'Notification dialog'
                                            }
                                        }
                                    }));
                                }
                            }
                            break;
                        default:
                    }
                }
            }
        }))
    );

    @Effect({ dispatch: false }) getUserFailure = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.GET_USER_FAILURE),
        map(() => this.authService.clearSession())
    );

    @Effect() checkSession = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.CHECK_SESSION),
        map(() => new fromAuth.SessionStatusAction({ authenticated: this.authService.hasSession() }))
    );

    @Effect({ dispatch: false }) sessionStatus = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.SESSION_STATUS),
        map((action: fromAuth.SessionStatusAction) => action.payload),
        map((payload: { authenticated: boolean }) => payload.authenticated),
        map((authenticated: boolean) => {
            if (authenticated) {
                this.store.dispatch(new fromAuth.GetUserAction());
            }
        })
    );

    @Effect() init = defer(() => {
        return of(new fromAuth.CheckSessionAction());
    });

}
