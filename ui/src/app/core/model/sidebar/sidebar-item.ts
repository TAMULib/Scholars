import { Observable } from 'rxjs';

import { Params } from '@angular/router';
import { Action } from '@ngrx/store';

export enum SidebarItemType {
    FACET = 'FACET',
    ACTION = 'ACTION'
}

export interface SidebarItem {
    type: SidebarItemType;
    label: Observable<string>;
    facet?: any;
    route?: string[];
    queryParams?: Params;
    action?: Action;
    selected?: boolean;
    parenthetical?: number;
    classes?: string;
}
