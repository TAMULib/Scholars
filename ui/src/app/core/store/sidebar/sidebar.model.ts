import { Observable } from 'rxjs';

export interface SidebarItem {
    label: Observable<string>;
    route: string[];
    additionalClass?: string;
}

export interface SidebarSection {
    title: Observable<string>;
    items: SidebarItem[];
    additionalClass?: string;
    collapsible: boolean;
}

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsible: boolean;
    additionalClass?: string;
}
