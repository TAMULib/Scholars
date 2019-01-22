import { Action } from '@ngrx/store';

export enum LanguageActionTypes {
    SET_LANGUAGE = '[Language] set language',
    SET_LANGUAGE_SUCCESS = '[Language] successfully set language',
    SET_LANGUAGE_FAILURE = '[Language] failed to set langauge',
    SET_DEFAULT_LANGUAGE = '[Language] set default',
    RESET_LANGUAGE = '[Language] reset to default'
}

export class SetLanguageAction implements Action {
    readonly type = LanguageActionTypes.SET_LANGUAGE;
    constructor(public payload: { language: string }) { }
}

export class SetLanguageSuccessAction implements Action {
    readonly type = LanguageActionTypes.SET_LANGUAGE_SUCCESS;
    constructor(public payload: { language: string }) { }
}

export class SetLanguageFailureAction implements Action {
    readonly type = LanguageActionTypes.SET_LANGUAGE_FAILURE;
    constructor(public payload: { error: any, language: string }) { }
}

export class SetDefaultLanguageAction implements Action {
    readonly type = LanguageActionTypes.SET_DEFAULT_LANGUAGE;
    constructor(public payload: { language: string }) { }
}

export class ResetLanguageToDefaultAction implements Action {
    readonly type = LanguageActionTypes.RESET_LANGUAGE;
}

export type LanguageActions =
    SetLanguageAction |
    SetLanguageSuccessAction |
    SetLanguageFailureAction |
    SetDefaultLanguageAction |
    ResetLanguageToDefaultAction;
