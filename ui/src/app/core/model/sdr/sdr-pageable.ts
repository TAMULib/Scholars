import { SdrSort } from './sdr-sort';

export interface SdrPageable {
    readonly offset: number;
    readonly paged: boolean;
    readonly pageNumber: number;
    readonly pageSize: number;
    readonly sort: SdrSort;
    readonly unpaged: boolean;
}
