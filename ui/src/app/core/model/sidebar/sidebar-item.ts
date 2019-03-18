import { Observable } from 'rxjs';

import { Params } from '@angular/router';

export interface SidebarItem {
    label: Observable<string>;
    route: string[];
    queryParams?: Params;
    selected?: boolean;
    total?: number;
    classes?: string;
}
