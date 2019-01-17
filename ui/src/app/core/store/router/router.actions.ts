import { Action } from '@ngrx/store';
import { NavigationExtras } from '@angular/router';

export type RouterNavigation = Readonly<{
    path: any[];
    query?: object;
    extras?: NavigationExtras;
}>;

export enum RouterActionTypes {
    GO = '[Router] go',
    BACK = '[Router] back',
    FORWARD = '[Router] forward',
    CHANGED = '[Router] changed'
}

export class Go implements Action {
    readonly type = RouterActionTypes.GO;
    constructor(public payload: RouterNavigation) { }
}

export class Back implements Action {
    readonly type = RouterActionTypes.BACK;
}

export class Forward implements Action {
    readonly type = RouterActionTypes.FORWARD;
}

export class Changed implements Action {
    readonly type = RouterActionTypes.CHANGED;
}

export type RouterActions =
    Go |
    Back |
    Forward |
    Changed;
