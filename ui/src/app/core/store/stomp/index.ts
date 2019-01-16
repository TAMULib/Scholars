import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromStomp from './stomp.reducer';

export const selectStompState = createFeatureSelector<fromStomp.StompState>('stomp');

export const selectIsConnected = createSelector(selectStompState, fromStomp.isConnected);
export const selectIsDisconnected = createSelector(selectStompState, fromStomp.isDisconnected);
export const selectSubscriptions = createSelector(selectStompState, fromStomp.getSubscriptions);
