import { StompActions, StompActionTypes } from './stomp.actions';
import { StompSubscription } from './stomp.model';

export type StompState = Readonly<{
    connecting: boolean;
    connected: boolean;
    handles: Map<string, (message: any) => void>;
    subscriptions: Map<string, StompSubscription>;
}>;

export const initialState: StompState = {
    connecting: false,
    connected: false,
    handles: new Map<string, (message: any) => void>(),
    subscriptions: new Map<string, StompSubscription>()
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
            state.handles.set(action.payload.channel, action.payload.handle);
            return state;
        case StompActionTypes.SUBSCRIBE_SUCCESS:
            state.subscriptions.set(action.payload.channel, action.payload.subscription);
            return state;
        case StompActionTypes.SUBSCRIBE_FAILURE:
            console.log(action);
            state.handles.delete(action.payload.channel);
            return state;
        case StompActionTypes.UNSUBSCRIBE_SUCCESS:
            state.handles.delete(action.payload.channel);
            state.subscriptions.delete(action.payload.channel);
            return state;
        default:
            return state;
    }
}

export const isConnected = (state: StompState) => state.connected;
export const isDisconnected = (state: StompState) => !state.connected;
export const getSubscriptions = (state: StompState) => state.subscriptions;
