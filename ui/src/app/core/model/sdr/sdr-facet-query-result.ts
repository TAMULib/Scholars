import { SdrSort } from './sdr-sort';

export interface SdrFacetQueryResult {
    readonly content: any[];
    readonly empty: boolean;
    readonly first: boolean;
    readonly last: boolean;
    readonly number: boolean;
    readonly nunberOfElements: number;
    readonly pageable: string;
    readonly size: boolean;
    readonly sort: SdrSort;
    readonly totalElements: number;
    readonly totalPages: number;
}
