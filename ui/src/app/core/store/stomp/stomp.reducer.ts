import { StompActions, StompActionTypes } from './stomp.actions';
import { Channel } from './stomp.model';

export type StompState = Readonly<{
    connecting: boolean;
    connected: boolean;
    subscriptions: Map<string, Channel>;
}>;

export const initialState: StompState = {
    connecting: false,
    connected: false,
    subscriptions: new Map<string, Channel>()
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
        case StompActionTypes.CONNECT_FAILURE:
            console.log(action);
            return {
                ...state,
                connecting: false,
                connected: false
            };
        case StompActionTypes.DISCONNECT_SUCCESS:
            return {
                ...state,
                connected: false
            };
        case StompActionTypes.SUBSCRIBE:
            state.subscriptions.set(action.payload.channel, { handle: action.payload.handle });
            return state;
        case StompActionTypes.SUBSCRIBE_SUCCESS:
            state.subscriptions.set(action.payload.channel, Object.assign(state.subscriptions.get(action.payload.channel), { subscription: action.payload.subscription }));
            return state;
        case StompActionTypes.SUBSCRIBE_FAILURE:
            console.log(action);
            return state;
        case StompActionTypes.UNSUBSCRIBE_SUCCESS:
            state.subscriptions.delete(action.payload.channel);
            return state;
        default:
            return state;
    }
}

export const isConnected = (state: StompState) => state.connected;
export const isDisconnected = (state: StompState) => !state.connected;
export const getSubscriptions = (state: StompState) => state.subscriptions;
