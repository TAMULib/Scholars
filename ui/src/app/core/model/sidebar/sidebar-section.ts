import { Observable } from 'rxjs';
import { SidebarItem } from './';

export interface SidebarSection {
    label?: Observable<string>;
    items: SidebarItem[];
    collapsible: boolean;
    classes?: string[];
}
