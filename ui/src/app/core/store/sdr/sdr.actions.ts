import { Action } from '@ngrx/store';

export enum SdrActionTypes {
    PAGE = 'page resources',
    PAGE_SUCCESS = 'sucessfully paged resources',
    PAGE_FAILURE = 'failed paging resources',
    POST = 'post resource',
    POST_SUCCESS = 'sucessfully posted resource',
    POST_FAILURE = 'failed posting resource',
    PUT = 'put resource',
    PUT_SUCCESS = 'sucessfully put resource',
    PUT_FAILURE = 'failed putting resource',
    PATCH = 'patch resource',
    PATCH_SUCCESS = 'sucessfully patched resource',
    PATCH_FAILURE = 'failed patching resource',
    DELETE = 'delete resource',
    DELETE_SUCCESS = 'sucessfully deleteed resource',
    DELETE_FAILURE = 'failed deleting resource',
    CLEAR = 'clear resources'
}

export const getSdrAction = (actionType: SdrActionTypes, name: string): string => {
    return `[${name}] ${actionType}`;
};


export class PageResourcesAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PAGE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PageResourcesSuccessAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PAGE_SUCCESS, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PageResourcesFailureAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PAGE_FAILURE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PostResourceAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.POST, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PostResourceSuccessAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.POST_SUCCESS, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PostResourceFailureAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.POST_FAILURE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PutResourceAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PUT, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PutResourceSuccessAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PUT_SUCCESS, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PutResourceFailureAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PUT_FAILURE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PatchResourceAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PATCH, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PatchResourceSuccessAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PATCH_SUCCESS, this.name);
    constructor(public name: string, public payload: any) { }
}

export class PatchResourceFailureAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.PATCH_FAILURE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class DeleteResourceAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.DELETE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class DeleteResourceSuccessAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.DELETE_SUCCESS, this.name);
    constructor(public name: string, public payload?: any) { }
}

export class DeleteResourceFailureAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.DELETE_FAILURE, this.name);
    constructor(public name: string, public payload: any) { }
}

export class ClearResourcesAction implements Action {
    readonly type = getSdrAction(SdrActionTypes.CLEAR, this.name);
    constructor(public name: string, public payload?: any) { }
}

export type SdrActions =
    PageResourcesAction |
    PageResourcesSuccessAction |
    PageResourcesFailureAction |
    PostResourceAction |
    PostResourceSuccessAction |
    PostResourceFailureAction |
    PutResourceAction |
    PutResourceSuccessAction |
    PutResourceFailureAction |
    PatchResourceAction |
    PatchResourceSuccessAction |
    PatchResourceFailureAction |
    DeleteResourceAction |
    DeleteResourceSuccessAction |
    DeleteResourceFailureAction |
    ClearResourcesAction;
