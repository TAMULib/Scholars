import { Observable } from 'rxjs';

export interface SidebarItem {
    label: Observable<string>;
    route: string[];
    additionalClass?: string;
}
