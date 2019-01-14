import { RouterStateSnapshot, Params } from '@angular/router';

import * as fromRouter from '@ngrx/router-store';

export interface CustomRouterState {
    url: string;
    params: Params;
    queryParams: Params;
    data: any;
}

export class CustomRouterStateSerializer implements fromRouter.RouterStateSerializer<CustomRouterState> {
    serialize(routerState: RouterStateSnapshot): CustomRouterState {
        const { url } = routerState;
        const params = routerState.root.params;
        const queryParams = routerState.root.queryParams;
        const data = routerState.root.data;
        return { url, params, queryParams, data };
    }
}

