import { SidebarSection } from './';

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsible: boolean;
    classes?: string[];
}
