export interface SidebarItem {
    label: string;
    route: string[];
    additionalClass?: string;
}

export interface SidebarSection {
    title: string;
    items: SidebarItem[];
    additionalClass?: string;
    collapsible: boolean;
}

export interface SidebarMenu {
    sections: SidebarSection[];
    collapsible: boolean;
    additionalClass?: string;
}
