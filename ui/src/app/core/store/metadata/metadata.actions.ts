import { Action } from '@ngrx/store';
import { MetaDefinition } from '@angular/platform-browser';

export enum MetadataActionTypes {
    CLEAR_TAGS = '[Metadata] clear tags',
    SET_TAGS = '[Metadata] set tags',
    ADD_TAGS = '[Metadata] add tags',
    REMOVE_TAGS = '[Metadata] remove tags',
    ADD_TAG = '[Metadata] add tag',
    REMOVE_TAG = '[Metadata] remove tag',
    UPDATE_TAG = '[Metadata] update tag'
}

export class ClearMetadataTagsAction implements Action {
    readonly type = MetadataActionTypes.CLEAR_TAGS;
}

export class SetMetadataTagsAction implements Action {
    readonly type = MetadataActionTypes.SET_TAGS;
    constructor(public payload: { tags: MetaDefinition[] }) { }
}

export class AddMetadataTagsAction implements Action {
    readonly type = MetadataActionTypes.ADD_TAGS;
    constructor(public payload: { tags: MetaDefinition[] }) { }
}

export class RemoveMetadataTagsAction implements Action {
    readonly type = MetadataActionTypes.REMOVE_TAGS;
    constructor(public payload: { tags: MetaDefinition[] }) { }
}

export class AddMetadataTagAction implements Action {
    readonly type = MetadataActionTypes.ADD_TAG;
    constructor(public payload: { tag: MetaDefinition }) { }
}

export class RemoveMetadataTagAction implements Action {
    readonly type = MetadataActionTypes.REMOVE_TAG;
    constructor(public payload: { selector: string }) { }
}

export class UpdateMetadataTagAction implements Action {
    readonly type = MetadataActionTypes.UPDATE_TAG;
    constructor(public payload: { tag: MetaDefinition }) { }
}

export type MetadataActions =
    ClearMetadataTagsAction |
    SetMetadataTagsAction |
    AddMetadataTagsAction |
    RemoveMetadataTagsAction |
    AddMetadataTagAction |
    RemoveMetadataTagAction |
    UpdateMetadataTagAction;
