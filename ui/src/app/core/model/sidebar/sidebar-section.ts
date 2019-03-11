import { Observable } from 'rxjs';
import { SidebarItem } from './';
import { Collapsible } from '../theme/collapsible';

export interface SidebarSection {
    title: Observable<string>;
    items: SidebarItem[];
    collapsible: Collapsible;
    classes?: string;
}
