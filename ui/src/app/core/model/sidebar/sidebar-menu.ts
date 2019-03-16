import { SidebarSection } from './';

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsed: boolean;
    classes?: string;
}
