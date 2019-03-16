import { SidebarSection } from './';

export interface SidebarMenu {
    sections: SidebarSection[];
    open: boolean;
    classes?: string;
}
