import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import * as fromMetadata from './metadata.reducer';

export const selectMetadataState = createFeatureSelector<fromMetadata.MetadataState>('metadata');

export const selectMetadataTags = createSelector(selectMetadataState, fromMetadata.getMetadataTags);
