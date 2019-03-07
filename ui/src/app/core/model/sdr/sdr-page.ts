import { SdrSortBy } from './sdr-sort-by';

export interface SdrPageRequest {
    collection: string;
    readonly number: number;
    readonly size: number;
    readonly sort?: SdrSortBy;
}

export interface SdrPage extends SdrPageRequest {
    readonly totalElements: number;
    readonly totalPages: number;
}
