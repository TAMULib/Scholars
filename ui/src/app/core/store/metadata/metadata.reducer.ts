import { MetadataActions, MetadataActionTypes } from './metadata.actions';
import { MetaDefinition } from '@angular/platform-browser';

export type MetadataState = Readonly<{
    tags: MetaDefinition[];
}>;

export const initialState: MetadataState = {
    tags: []
};

export function reducer(state = initialState, action: MetadataActions): MetadataState {
    switch (action.type) {
        case MetadataActionTypes.ADD_TAGS:
            return {
                ...state,
                tags: action.payload.tags
            };
        case MetadataActionTypes.REMOVE_TAGS:
            return {
                ...state,
                tags: []
            };
        case MetadataActionTypes.ADD_TAG:
            state.tags.push(action.payload.tag);
            return {
                ...state
            };
        case MetadataActionTypes.REMOVE_TAG:
            return {
                ...state,
                tags: state.tags.filter(tag => tag.name !== action.payload.selector)
            };
        case MetadataActionTypes.UPDATE_TAG:
            for (const i in state.tags) {
                if (state.tags.hasOwnProperty(i)) {
                    if (state.tags[i].name === action.payload.tag.name) {
                        state.tags[i] = action.payload.tag;
                        break;
                    }
                }
            }
            return {
                ...state
            };
        default:
            return state;
    }
}

export const getMetadataTags = (state: MetadataState) => state.tags;
