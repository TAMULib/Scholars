import { OperationKey, FacetSort } from '../view';

export enum Direction {
    ASC = 'asc',
    DESC = 'desc'
}

export interface Sort {
    readonly name: string;
    readonly direction: Direction;
}

export interface Pageable {
    readonly number: number;
    readonly size: number;
    readonly sort: Sort[];
}

export interface Indexable {
    readonly field: string;
    readonly operationKey: OperationKey;
    readonly option: string;
}

export interface Facetable {
    readonly field: string;
    readonly limit?: number;
    readonly offset?: number;
    readonly sort?: FacetSort;
    readonly filter?: string;
}

export interface SdrRequest {
    readonly pageable?: Pageable;
    readonly indexable?: Indexable;
    readonly facets?: Facetable[];
    readonly query?: string;
}
