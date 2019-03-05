import { SdrPageRequest } from './sdr-page';

export interface SdrDiscoverFilter {
    readonly filter?: string;
    readonly limit?: number;
    readonly offset?: number;
    readonly sort?: number;
}

export interface SdrDiscoverRequest extends SdrPageRequest {
    readonly facets?: string[];
    readonly query?: string;
    readonly type?: SdrDiscoverFilter;
}
