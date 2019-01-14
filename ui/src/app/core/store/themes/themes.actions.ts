import { SafeStyle } from '@angular/platform-browser';
import { Action } from '@ngrx/store';

import { SdrCollection } from '../../model/sdr';
import { SdrPageRequest } from '../../model/sdr/sdr-page';
import { Theme } from '../../model/theme';

export enum ThemesActionTypes {
    LOAD_ACTIVE = '[Themes] load active theme',
    LOAD_ACTIVE_SUCCESS = '[Themes] sucessfully loaded active theme',
    LOAD_ACTIVE_FAILURE = '[Themes] failed loading active theme',
    APPLY_ACTIVE = '[Themes] apply active theme',
    APPLY_ACTIVE_SUCCESS = '[Themes] apply active theme success',
    APPLY_ACTIVE_FAILURE = '[Themes] apply active theme failure',
    LOAD = '[Themes] load themes',
    LOAD_SUCCESS = '[Themes] sucessfully loaded themes',
    LOAD_FAILURE = '[Themes] failed loading themes',
    CLEAR = '[Themes] clear themes'
}

export class LoadActiveThemeAction implements Action {
    readonly type = ThemesActionTypes.LOAD_ACTIVE;
}

export class LoadActiveThemeSuccessAction implements Action {
    readonly type = ThemesActionTypes.LOAD_ACTIVE_SUCCESS;
    constructor(public payload: { theme: Theme }) { }
}

export class LoadActiveThemeFailureAction implements Action {
    readonly type = ThemesActionTypes.LOAD_ACTIVE_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class ApplyActiveThemeAction implements Action {
    readonly type = ThemesActionTypes.APPLY_ACTIVE;
    constructor(public payload: { theme: Theme }) { }
}

export class ApplyActiveThemeSuccessAction implements Action {
    readonly type = ThemesActionTypes.APPLY_ACTIVE_SUCCESS;
    constructor(public payload: { style: SafeStyle }) { }
}

export class ApplyActiveThemeFailureAction implements Action {
    readonly type = ThemesActionTypes.APPLY_ACTIVE_FAILURE;
    constructor(public payload: { error: string }) { }
}

export class LoadThemesAction implements Action {
    readonly type = ThemesActionTypes.LOAD;
    constructor(public payload: { page: SdrPageRequest }) { }
}

export class LoadThemesSuccessAction implements Action {
    readonly type = ThemesActionTypes.LOAD_SUCCESS;
    constructor(public payload: { collection: SdrCollection }) { }
}

export class LoadThemesFailureAction implements Action {
    readonly type = ThemesActionTypes.LOAD_FAILURE;
    constructor(public payload: { response: any }) { }
}

export class ClearThemesAction implements Action {
    readonly type = ThemesActionTypes.CLEAR;
}

export type ThemesActions =
    LoadActiveThemeAction |
    LoadActiveThemeSuccessAction |
    LoadActiveThemeFailureAction |
    ApplyActiveThemeAction |
    ApplyActiveThemeSuccessAction |
    ApplyActiveThemeFailureAction |
    LoadThemesAction |
    LoadThemesSuccessAction |
    LoadThemesFailureAction |
    ClearThemesAction;
