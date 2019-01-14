import { Action } from '@ngrx/store';

import { SdrCollection } from '../../model/sdr';
import { SdrPageRequest } from '../../model/sdr/sdr-page';

export enum UsersActionTypes {
    LOAD = '[Users] load users',
    LOAD_SUCCESS = '[Users] sucessfully loaded users',
    LOAD_FAILURE = '[Users] failed loading users',
    CLEAR = '[Users] clear users'
}

export class LoadUsersAction implements Action {
    readonly type = UsersActionTypes.LOAD;
    constructor(public payload: { page: SdrPageRequest }) { }
}

export class LoadUsersSuccessAction implements Action {
    readonly type = UsersActionTypes.LOAD_SUCCESS;
    constructor(public payload: { collection: SdrCollection }) { }
}

export class LoadUsersFailureAction implements Action {
    readonly type = UsersActionTypes.LOAD_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class ClearUsersAction implements Action {
    readonly type = UsersActionTypes.CLEAR;
}

export type UsersActions =
    LoadUsersAction |
    LoadUsersSuccessAction |
    LoadUsersFailureAction |
    ClearUsersAction;
