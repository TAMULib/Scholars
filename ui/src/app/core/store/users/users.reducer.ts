import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';

import { UsersActions, UsersActionTypes } from './users.actions';

import { User } from '../../model/user';
import { SdrPage } from '../../model/sdr';
import { SdrCollectionLinks } from '../../model/sdr';

export interface UsersState extends EntityState<User> {
    page: SdrPage;
    links: SdrCollectionLinks;
    loading: boolean;
    updating: boolean;
    error: any;
}

export const adapter: EntityAdapter<User> = createEntityAdapter<User>({
    selectId: (user: User) => user.email,
});

export const initialState: UsersState = adapter.getInitialState({
    page: undefined,
    links: undefined,
    loading: false,
    updating: false,
    error: undefined
});

export function reducer(state = initialState, action: UsersActions): UsersState {
    switch (action.type) {
        case UsersActionTypes.LOAD:
            return {
                ...state,
                loading: true,
                error: undefined
            };
        case UsersActionTypes.LOAD_SUCCESS:
            return adapter.addAll(action.payload.collection._embedded.users, {
                ...state,
                page: Object.assign(action.payload.collection.page, { number: action.payload.collection.page.number + 1 }),
                links: action.payload.collection._links,
                loading: false,
                error: undefined
            });
        case UsersActionTypes.LOAD_FAILURE:
            console.error(action);
            return {
                ...state,
                loading: false,
                error: action.payload.response.error
            };
        case UsersActionTypes.UPDATE:
            return {
                ...state,
                updating: true,
                error: undefined
            };
        case UsersActionTypes.UPDATE_SUCCESS:
            return {
                ...state,
                updating: false,
                error: undefined
            };
        case UsersActionTypes.UPDATE_FAILURE:
            console.error(action);
            return {
                ...state,
                updating: false,
                error: action.payload.response.error
            };
        case UsersActionTypes.CLEAR:
            return {
                ...state,
                page: undefined
            };
        default:
            return state;
    }
}

const { selectIds, selectEntities, selectAll, selectTotal } = adapter.getSelectors();

export const selectUsersIds = selectIds;
export const selectUsersEntities = selectEntities;
export const selectAllUsers = selectAll;
export const selectUsersTotal = selectTotal;

export const isUsersLoading = (state: UsersState) => state.loading;
export const isUserUpdating = (state: UsersState) => state.loading;
export const getUsersError = (state: UsersState) => state.error;

export const getUsersPage = (state: UsersState) => state.page;
export const getUsersLinks = (state: UsersState) => state.links;
