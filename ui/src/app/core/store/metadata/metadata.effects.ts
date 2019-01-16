import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { MetaDefinition } from '@angular/platform-browser';

import { map } from 'rxjs/operators';

import { MetadataService } from '../../service/metadata.service';

import * as fromMetadata from './metadata.actions';

@Injectable()
export class MetadataEffects {

    constructor(
        private actions: Actions,
        private metadataService: MetadataService
    ) {

    }

    @Effect({ dispatch: false }) addTags = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.ADD_TAGS),
        map((action: fromMetadata.AddMetadataTagsAction) => action.payload),
        map((payload: { tags: MetaDefinition[] }) => payload.tags),
        map((tags: MetaDefinition[]) => this.metadataService.addTags(tags))
    );

    @Effect({ dispatch: false }) addTag = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.ADD_TAG),
        map((action: fromMetadata.AddMetadataTagAction) => action.payload),
        map((payload: { tag: MetaDefinition }) => payload.tag),
        map((tag: MetaDefinition) => this.metadataService.addTag(tag))
    );

    @Effect({ dispatch: false }) removeTag = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.REMOVE_TAG),
        map((action: fromMetadata.RemoveMetadataTagAction) => action.payload),
        map((payload: { selector: string }) => payload.selector),
        map((selector: string) => this.metadataService.removeTag(selector))
    );

    @Effect({ dispatch: false }) updateTag = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.UPDATE_TAG),
        map((action: fromMetadata.AddMetadataTagAction) => action.payload),
        map((payload: { tag: MetaDefinition }) => payload.tag),
        map((tag: MetaDefinition) => this.metadataService.updateTag(tag))
    );

}
