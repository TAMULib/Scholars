import { RouterStateSnapshot, Params } from '@angular/router';

import * as fromRouter from '@ngrx/router-store';

export type CustomRouterState = Readonly<{
    url: string;
    params: Params;
    queryParams: Params;
    data: any;
}>;

export class CustomRouterStateSerializer implements fromRouter.RouterStateSerializer<CustomRouterState> {
    serialize(routerState: RouterStateSnapshot): CustomRouterState {
        const { url } = routerState;
        const queryParams = routerState.root.queryParams;

        let route = routerState.root;
        while (route.firstChild) {
            route = route.firstChild;
        }

        const params = route.params;
        const data = route.data;

        return { url, params, queryParams, data };
    }
}

