import { AuthActions, AuthActionTypes } from './auth.actions';
import { RouterNavigation } from '../router/router.actions';
import { User } from '../../model/user';
import { RegistrationRequest } from '../../model/request/registration.request';

export interface AuthState {
    checkingSession: boolean;
    loggingIn: boolean;
    loggingOut: boolean;
    submittingRegistration: boolean;
    confirmingRegistration: boolean;
    completingRegistration: boolean;
    gettingUser: boolean;
    authenticated: boolean;
    redirect: RouterNavigation;
    user: User;
    registration: RegistrationRequest;
    error: any;
}

export const initialState: AuthState = {
    checkingSession: false,
    loggingIn: false,
    loggingOut: false,
    submittingRegistration: false,
    confirmingRegistration: false,
    completingRegistration: false,
    gettingUser: false,
    authenticated: false,
    redirect: undefined,
    user: undefined,
    registration: undefined,
    error: undefined
};

export function reducer(state = initialState, action: AuthActions): AuthState {
    switch (action.type) {
        case AuthActionTypes.CHECK_SESSION:
            return {
                ...state,
                checkingSession: true
            };
        case AuthActionTypes.SESSION_STATUS:
            return {
                ...state,
                authenticated: action.payload.authenticated,
                checkingSession: false
            };
        case AuthActionTypes.LOGIN:
            return {
                ...state,
                loggingIn: true
            };
        case AuthActionTypes.LOGIN_SUCCESS:
            return {
                ...state,
                loggingIn: false,
                authenticated: true,
                user: action.payload.user
            };
        case AuthActionTypes.LOGIN_FAILURE:
            return {
                ...state,
                loggingIn: false,
                error: action.payload.response
            };
        case AuthActionTypes.SUBMIT_REGISTRATION:
            return {
                ...state,
                submittingRegistration: true
            };
        case AuthActionTypes.SUBMIT_REGISTRATION_SUCCESS:
            return {
                ...state,
                submittingRegistration: false,
                registration: action.payload.registration
            };
        case AuthActionTypes.SUBMIT_REGISTRATION_FAILURE:
            return {
                ...state,
                submittingRegistration: false,
                error: action.payload.response
            };
        case AuthActionTypes.CONFIRM_REGISTRATION:
            return {
                ...state,
                confirmingRegistration: true
            };
        case AuthActionTypes.CONFIRM_REGISTRATION_SUCCESS:
            return {
                ...state,
                confirmingRegistration: false,
                registration: action.payload.registration
            };
        case AuthActionTypes.CONFIRM_REGISTRATION_FAILURE:
            return {
                ...state,
                confirmingRegistration: false,
                error: action.payload.response
            };
        case AuthActionTypes.COMPLETE_REGISTRATION:
            return {
                ...state,
                completingRegistration: true
            };
        case AuthActionTypes.COMPLETE_REGISTRATION_SUCCESS:
            return {
                ...state,
                completingRegistration: false,
                registration: undefined
            };
        case AuthActionTypes.COMPLETE_REGISTRATION_FAILURE:
            return {
                ...state,
                completingRegistration: false,
                error: action.payload.response
            };
        case AuthActionTypes.GET_USER:
            return {
                ...state,
                gettingUser: true,
            };
        case AuthActionTypes.GET_USER_SUCCESS:
            return {
                ...state,
                gettingUser: false,
                authenticated: true,
                user: action.payload.user
            };
        case AuthActionTypes.GET_USER_FAILURE:
            return {
                ...state,
                gettingUser: false,
                authenticated: false,
                error: action.payload.response
            };
        case AuthActionTypes.LOGOUT:
            return {
                ...state,
                loggingOut: true
            };
        case AuthActionTypes.LOGOUT_SUCCESS:
            return {
                ...state,
                loggingOut: false,
                authenticated: false,
                user: undefined
            };
        case AuthActionTypes.LOGOUT_FAILURE:
            return {
                ...state,
                loggingOut: false,
                error: action.payload.response
            };
        case AuthActionTypes.SET_LOGIN_REDIRECT:
            return {
                ...state,
                redirect: action.payload
            };
        case AuthActionTypes.UNSET_LOGIN_REDIRECT:
            return {
                ...state,
                redirect: undefined
            };
        default:
            return state;
    }
}

export const isCheckingSession = (state: AuthState) => state.checkingSession;
export const isLoggingIn = (state: AuthState) => state.loggingIn;
export const isLoggingOut = (state: AuthState) => state.loggingOut;
export const isSubmittingRegistration = (state: AuthState) => state.submittingRegistration;
export const isConfirmingRegistration = (state: AuthState) => state.confirmingRegistration;
export const isCompletingRegistration = (state: AuthState) => state.completingRegistration;
export const isGettingUser = (state: AuthState) => state.gettingUser;
export const isAuthenticated = (state: AuthState) => state.authenticated;
export const getUser = (state: AuthState) => state.user;
export const getRegistration = (state: AuthState) => state.registration;
export const getLoginRedirect = (state: AuthState) => state.redirect;
export const getError = (state: AuthState) => state.error;
