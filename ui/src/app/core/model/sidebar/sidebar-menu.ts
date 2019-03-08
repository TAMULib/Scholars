import { SidebarSection } from './';
import { Collapsable } from '../theme/collapsable';

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsable: Collapsable;
    classes?: string;
}
