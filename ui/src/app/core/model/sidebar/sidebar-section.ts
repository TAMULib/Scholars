import { Observable } from 'rxjs';
import { SidebarItem } from './';
import { Collapsable } from '../theme/collapsable';

export interface SidebarSection {
    title: Observable<string>;
    items: SidebarItem[];
    collapsable: Collapsable;
    classes?: string;
}
