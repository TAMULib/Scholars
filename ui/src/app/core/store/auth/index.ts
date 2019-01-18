import { createSelector, createFeatureSelector } from '@ngrx/store';
import { Role, User } from '../../model/user';

import * as fromAuth from './auth.reducer';

export const selectAuthState = createFeatureSelector<fromAuth.AuthState>('auth');

export const selectIsLoggingIn = createSelector(selectAuthState, fromAuth.isLoggingIn);
export const selectIsLoggingOut = createSelector(selectAuthState, fromAuth.isLoggingOut);
export const selectIsSubmittingRegistration = createSelector(selectAuthState, fromAuth.isSubmittingRegistration);
export const selectIsCompletingRegistration = createSelector(selectAuthState, fromAuth.isConfirmingRegistration);
export const selectIsConfirmingRegistration = createSelector(selectAuthState, fromAuth.isCompletingRegistration);
export const selectIsGettingUser = createSelector(selectAuthState, fromAuth.isGettingUser);
export const selectIsAuthenticated = createSelector(selectAuthState, fromAuth.isAuthenticated);
export const selectUser = createSelector(selectAuthState, fromAuth.getUser);
export const selectLoginRedirect = createSelector(selectAuthState, fromAuth.getLoginRedirect);
export const selectError = createSelector(selectAuthState, fromAuth.getError);

export const selectHasRole = (role: Role) => createSelector(selectUser, (user: User) => {
    if (user) {
        const roles = Object.values(Role);
        return roles.indexOf(Role[user.role]) >= roles.indexOf(role);
    }
    return false;
});

export const selectIsSuperadmin = createSelector(selectUser, (user: User) => {
    return user && Role[user.role] === Role.ROLE_SUPER_ADMIN;
});

export const selectIsAdmin = createSelector(selectUser, (user: User) => {
    return user && Role[user.role] === Role.ROLE_ADMIN;
});

export const selectIsUser = createSelector(selectUser, (user: User) => {
    return user && Role[user.role] === Role.ROLE_USER;
});
