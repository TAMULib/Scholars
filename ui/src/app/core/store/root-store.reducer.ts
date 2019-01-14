import * as fromStore from './root-store.actions';

export function universalMetaReducer(reducer) {
    return (state, action) => {
        switch (action.type) {
            case fromStore.StoreActionTypes.REHYDRATE:
                state = Object.assign({}, state, action.payload);
                break;
            default:
                break;
        }
        return reducer(state, action);
    };
}
