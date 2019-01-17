import { SafeStyle } from '@angular/platform-browser';
import { Action } from '@ngrx/store';

import { Theme } from '../../model/theme';

export enum ThemeActionTypes {
    LOAD_ACTIVE = '[Themes] load active theme',
    LOAD_ACTIVE_SUCCESS = '[Themes] sucessfully loaded active theme',
    LOAD_ACTIVE_FAILURE = '[Themes] failed loading active theme',
    APPLY_ACTIVE = '[Themes] apply active theme',
    APPLY_ACTIVE_SUCCESS = '[Themes] apply active theme success',
    APPLY_ACTIVE_FAILURE = '[Themes] apply active theme failure'
}

export class LoadActiveThemeAction implements Action {
    readonly type = ThemeActionTypes.LOAD_ACTIVE;
}

export class LoadActiveThemeSuccessAction implements Action {
    readonly type = ThemeActionTypes.LOAD_ACTIVE_SUCCESS;
    constructor(public payload: { theme: Theme }) { }
}

export class LoadActiveThemeFailureAction implements Action {
    readonly type = ThemeActionTypes.LOAD_ACTIVE_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class ApplyActiveThemeAction implements Action {
    readonly type = ThemeActionTypes.APPLY_ACTIVE;
    constructor(public payload: { theme: Theme }) { }
}

export class ApplyActiveThemeSuccessAction implements Action {
    readonly type = ThemeActionTypes.APPLY_ACTIVE_SUCCESS;
    constructor(public payload: { style: SafeStyle }) { }
}

export class ApplyActiveThemeFailureAction implements Action {
    readonly type = ThemeActionTypes.APPLY_ACTIVE_FAILURE;
    constructor(public payload: { error: string }) { }
}

export type ThemeActions =
    LoadActiveThemeAction |
    LoadActiveThemeSuccessAction |
    LoadActiveThemeFailureAction |
    ApplyActiveThemeAction |
    ApplyActiveThemeSuccessAction |
    ApplyActiveThemeFailureAction;
