import { Observable } from 'rxjs';
import { SidebarItem } from './';

export interface SidebarSection {
    title: Observable<string>;
    items: SidebarItem[];
    collapsible: boolean;
    classes?: string;
}
