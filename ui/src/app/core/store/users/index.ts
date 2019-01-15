import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import { User } from '../../model/user';

import * as fromUsers from './users.reducer';

export const selectUsersState = createFeatureSelector<fromUsers.UsersState>('users');

export const selectUsersIds = createSelector(selectUsersState, fromUsers.selectUsersIds);
export const selectUsersEntities = createSelector(selectUsersState, fromUsers.selectUsersEntities);
export const selectAllUsers = createSelector(selectUsersState, fromUsers.selectAllUsers);
export const selectUsersTotal = createSelector(selectUsersState, fromUsers.selectUsersTotal);

export const selectUsersError = createSelector(selectUsersState, fromUsers.getUsersError);
export const selectUsersIsLoading = createSelector(selectUsersState, fromUsers.isUsersLoading);
export const selectUserIsUpdating = createSelector(selectUsersState, fromUsers.isUserUpdating);

export const selectUsersPage = createSelector(selectUsersState, fromUsers.getUsersPage);
export const selectUsersLinks = createSelector(selectUsersState, fromUsers.getUsersLinks);

export const selectUsersByEmail = (email: string) => createSelector(selectAllUsers, (users: User[]) => {
    return users ? users.find(user => user.email === email) : undefined;
});
