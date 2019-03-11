import { Observable } from 'rxjs';

import { SidebarField } from './sidebar-field';
import { SidebarInput } from './sidebar-input';
import { Params } from '@angular/router';

export interface SidebarItem {
    label: Observable<string>;
    route: string[];
    queryParams?: Params;
    id?: string;
    icon?: string;
    link?: string;
    total?: number;
    checkbox?: SidebarInput;
    fields?: SidebarField[];
    classes?: string;
}
