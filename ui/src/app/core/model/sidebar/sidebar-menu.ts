import { SidebarSection } from './';
import { Collapsible } from '../theme/collapsible';

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsible: Collapsible;
    classes?: string;
}
