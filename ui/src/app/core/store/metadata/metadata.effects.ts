import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { MetaDefinition } from '@angular/platform-browser';
import { Router, ActivationStart } from '@angular/router';
import { Store, select } from '@ngrx/store';

import { filter, map, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../';

import { MetadataService } from '../../service/metadata.service';

import { selectMetadataTags } from './';

import * as fromMetadata from './metadata.actions';

@Injectable()
export class MetadataEffects {

    constructor(
        private actions: Actions,
        private router: Router,
        private store: Store<AppState>,
        private metadataService: MetadataService
    ) {
        this.listenForRouteDataTags();
    }

    @Effect({ dispatch: false }) clearTags = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.CLEAR_TAGS),
        withLatestFrom(this.store.pipe(select(selectMetadataTags))),
        map(([action, tags]) => this.metadataService.removeTags(tags))
    );

    @Effect() setTags = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.SET_TAGS),
        map((action: fromMetadata.SetMetadataTagsAction) => action.payload),
        withLatestFrom(this.store.pipe(select(selectMetadataTags))),
        map(([payload, tags]) => {
            this.metadataService.removeTags(tags);
            return new fromMetadata.AddMetadataTagsAction(payload);
        })
    );

    @Effect({ dispatch: false }) addTags = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.ADD_TAGS),
        map((action: fromMetadata.AddMetadataTagsAction) => action.payload),
        map((payload: { tags: MetaDefinition[] }) => payload.tags),
        map((tags: MetaDefinition[]) => this.metadataService.addTags(tags))
    );

    @Effect({ dispatch: false }) removeTags = this.actions.pipe(
        ofType(fromMetadata.MetadataActionTypes.REMOVE_TAGS),
        map((action: fromMetadata.RemoveMetadataTagsAction) => action.payload),
        map((payload: { tags: MetaDefinition[] }) => payload.tags),
        map((tags: MetaDefinition[]) => this.metadataService.removeTags(tags))
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

    private listenForRouteDataTags() {
        this.router.events.pipe(
            filter(event => event instanceof ActivationStart)
        ).subscribe((event: ActivationStart) => {
            if (event.snapshot.data.tags) {
                this.store.dispatch(new fromMetadata.SetMetadataTagsAction({
                    tags: event.snapshot.data.tags
                }));
            }
        });
    }

}
