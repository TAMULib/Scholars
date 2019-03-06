import { Observable } from 'rxjs';

import { SidebarField } from './sidebar-field';
import { SidebarInput } from './sidebar-input';

export interface SidebarItem {
    label: Observable<string>;
    route: string[];
    id?: string;
    icon?: string;
    link?: string;
    total?: Observable<string>;
    checkbox?: SidebarInput;
    fields?: SidebarField[];
    classes?: string;
}
