import { Action } from '@ngrx/store';

import { RouterNavigation } from '../router/router.actions';
import { User } from '../../model/user';
import { RegistrationRequest } from '../../model/request/registration.request';
import { LoginRequest } from '../../model/request/login.request';

export enum AuthActionTypes {
    LOGIN = '[Auth] login',
    LOGIN_SUCCESS = '[Auth] login sucessful',
    LOGIN_FAILURE = '[Auth] login failed',
    SUBMIT_REGISTRATION = '[Auth] submit registration',
    SUBMIT_REGISTRATION_SUCCESS = '[Auth] submit registration sucessful',
    SUBMIT_REGISTRATION_FAILURE = '[Auth] submit registration failed',
    CONFIRM_REGISTRATION = '[Auth] confirm registration',
    CONFIRM_REGISTRATION_SUCCESS = '[Auth] confirm registration sucessful',
    CONFIRM_REGISTRATION_FAILURE = '[Auth] confirm registration failed',
    COMPLETE_REGISTRATION = '[Auth] complete registration',
    COMPLETE_REGISTRATION_SUCCESS = '[Auth] complete registration sucessful',
    COMPLETE_REGISTRATION_FAILURE = '[Auth] complete registration failed',
    LOGOUT = '[Auth] logout',
    LOGOUT_SUCCESS = '[Auth] logout success',
    LOGOUT_FAILURE = '[Auth] logout failed',
    GET_USER = '[Auth] get user',
    GET_USER_SUCCESS = '[Auth] success getting user',
    GET_USER_FAILURE = '[Auth] failed getting user',
    CHECK_SESSION = '[Auth] check session',
    SESSION_STATUS = '[Auth] session status',
    SET_LOGIN_REDIRECT = '[Auth] set login redirect',
    UNSET_LOGIN_REDIRECT = '[Auth] unset login redirect'
}

export class LoginAction implements Action {
    readonly type = AuthActionTypes.LOGIN;
    constructor(public payload: { login: LoginRequest }) { }
}

export class LoginSuccessAction implements Action {
    readonly type = AuthActionTypes.LOGIN_SUCCESS;
    constructor(public payload: { user: User }) { }
}

export class LoginFailureAction implements Action {
    readonly type = AuthActionTypes.LOGIN_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class SubmitRegistrationAction implements Action {
    readonly type = AuthActionTypes.SUBMIT_REGISTRATION;
    constructor(public payload: { registration: RegistrationRequest }) { }
}

export class SubmitRegistrationSuccessAction implements Action {
    readonly type = AuthActionTypes.SUBMIT_REGISTRATION_SUCCESS;
    constructor(public payload: { registration: RegistrationRequest }) { }
}

export class SubmitRegistrationFailureAction implements Action {
    readonly type = AuthActionTypes.SUBMIT_REGISTRATION_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class ConfirmRegistrationAction implements Action {
    readonly type = AuthActionTypes.CONFIRM_REGISTRATION;
    constructor(public payload: { key: string }) { }
}

export class ConfirmRegistrationSuccessAction implements Action {
    readonly type = AuthActionTypes.CONFIRM_REGISTRATION_SUCCESS;
    constructor(public payload: { registration: RegistrationRequest }) { }
}

export class ConfirmRegistrationFailureAction implements Action {
    readonly type = AuthActionTypes.CONFIRM_REGISTRATION_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class CompleteRegistrationAction implements Action {
    readonly type = AuthActionTypes.COMPLETE_REGISTRATION;
    constructor(public payload: { registration: RegistrationRequest }) { }
}

export class CompleteRegistrationSuccessAction implements Action {
    readonly type = AuthActionTypes.COMPLETE_REGISTRATION_SUCCESS;
    constructor(public payload: { user: User }) { }
}

export class CompleteRegistrationFailureAction implements Action {
    readonly type = AuthActionTypes.COMPLETE_REGISTRATION_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class LogoutAction implements Action {
    readonly type = AuthActionTypes.LOGOUT;
}

export class LogoutSuccessAction implements Action {
    readonly type = AuthActionTypes.LOGOUT_SUCCESS;
    constructor(public payload: { message: string }) { }
}

export class LogoutFailureAction implements Action {
    readonly type = AuthActionTypes.LOGOUT_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class GetUserAction implements Action {
    readonly type = AuthActionTypes.GET_USER;
}

export class GetUserSuccessAction implements Action {
    readonly type = AuthActionTypes.GET_USER_SUCCESS;
    constructor(public payload: { user: User }) { }
}

export class GetUserFailureAction implements Action {
    readonly type = AuthActionTypes.GET_USER_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class CheckSessionAction implements Action {
    readonly type = AuthActionTypes.CHECK_SESSION;
}

export class SessionStatusAction implements Action {
    readonly type = AuthActionTypes.SESSION_STATUS;
    constructor(public payload: { authenticated: boolean }) { }
}

export class SetLoginRedirectAction implements Action {
    readonly type = AuthActionTypes.SET_LOGIN_REDIRECT;
    constructor(public payload: RouterNavigation) { }
}

export class UnsetLoginRedirectAction implements Action {
    readonly type = AuthActionTypes.UNSET_LOGIN_REDIRECT;
}

export type AuthActions =
    LoginAction |
    LoginSuccessAction |
    LoginFailureAction |
    SubmitRegistrationAction |
    SubmitRegistrationSuccessAction |
    SubmitRegistrationFailureAction |
    ConfirmRegistrationAction |
    ConfirmRegistrationSuccessAction |
    ConfirmRegistrationFailureAction |
    CompleteRegistrationAction |
    CompleteRegistrationSuccessAction |
    CompleteRegistrationFailureAction |
    LogoutAction |
    LogoutSuccessAction |
    LogoutFailureAction |
    GetUserAction |
    GetUserSuccessAction |
    GetUserFailureAction |
    CheckSessionAction |
    SessionStatusAction |
    SetLoginRedirectAction |
    UnsetLoginRedirectAction;
