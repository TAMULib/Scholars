import { StompActions, StompActionTypes } from './stomp.actions';

export interface StompState {
    connecting: boolean;
    connected: boolean;
    subscriptions: Map<string, any>;
}

const initialState: StompState = {
    connecting: false,
    connected: false,
    subscriptions: new Map<string, any>()
};

export function reducer(state = initialState, action: StompActions): StompState {
    switch (action.type) {
        case StompActionTypes.CONNECT:
            return {
                ...state,
                connecting: true
            };
        case StompActionTypes.CONNECT_SUCCESS:
            return {
                ...state,
                connecting: false,
                connected: true
            };
        case StompActionTypes.DISCONNECT_SUCCESS:
            return {
                ...state,
                connected: false
            };
        case StompActionTypes.SUBSCRIBE:
            return state;
        case StompActionTypes.SUBSCRIBE_SUCCESS:
            state.subscriptions.set(action.payload.channel, action.payload.subscription);
            return state;
        case StompActionTypes.UNSUBSCRIBE_SUCCESS:
            state.subscriptions.delete(action.payload);
            return state;
        default:
            return state;
    }
}

export const isConnected = (state: StompState) => state.connected;
export const getSubscriptions = (state: StompState) => state.subscriptions;
