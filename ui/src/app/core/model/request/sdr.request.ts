import { SdrSortBy } from '../sdr/sdr-sort-by';

export interface SdrRequest {
    collection: string;
    readonly number: number;
    readonly size: number;
    readonly sort?: SdrSortBy;
    readonly facets?: string[];
    readonly query?: string;
}
