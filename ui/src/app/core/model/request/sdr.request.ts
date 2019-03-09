export interface Sort {
    readonly name: string;
    readonly direction: string;
}

export interface Pageable {
    readonly number: number;
    readonly size: number;
    readonly sort: Sort[];
}

export interface Indexable {
    readonly field: string;
    readonly option: string;
}

export interface Facet {
    readonly field: string;
    readonly limit?: number;
    readonly offset?: number;
    readonly sort?: string;
    readonly filter?: string;
}

export interface SdrRequest {
    collection: string;
    readonly pageable: Pageable;
    readonly indexable?: Indexable;
    readonly facets?: Facet[];
    readonly query?: string;
}
