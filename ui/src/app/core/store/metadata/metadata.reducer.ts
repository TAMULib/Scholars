import { MetadataActions, MetadataActionTypes } from './metadata.actions';
import { MetaDefinition } from '@angular/platform-browser';

export type MetadataState = Readonly<{
    tags: MetaDefinition[];
}>;

export const initialState: MetadataState = {
    tags: []
};

export function reducer(state = initialState, action: MetadataActions): MetadataState {
    const remove = (tag: MetaDefinition) => {
        for (let i = state.tags.length - 1; i >= 0; i--) {
            if (state.tags[i].name === tag.name) {
                state.tags.splice(i, 1);
                break;
            }
        }
    };
    const update = (tag: MetaDefinition) => {
        for (const i in state.tags) {
            if (state.tags.hasOwnProperty(i)) {
                if (state.tags[i].name === tag.name) {
                    state.tags[i] = tag;
                    break;
                }
            }
        }
    };
    switch (action.type) {
        case MetadataActionTypes.ADD_TAGS:
            return {
                ...state,
                tags: state.tags.concat(action.payload.tags)
            };
        case MetadataActionTypes.REMOVE_TAGS:
            action.payload.tags.forEach((tag: MetaDefinition) => remove(tag));
            return {
                ...state
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
            update(action.payload.tag);
            return {
                ...state
            };
        default:
            return state;
    }
}

export const getMetadataTags = (state: MetadataState) => state.tags;
