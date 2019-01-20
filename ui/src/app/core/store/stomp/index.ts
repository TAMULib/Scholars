import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromStomp from './stomp.reducer';

export const selectStompState = createFeatureSelector<fromStomp.StompState>('stomp');

export const selectIsStompConnected = createSelector(selectStompState, fromStomp.isConnected);
export const selectIsStompDisconnected = createSelector(selectStompState, fromStomp.isDisconnected);
export const selectStompSubscriptions = createSelector(selectStompState, fromStomp.getSubscriptions);

export { StompSubscription } from './stomp.model';
