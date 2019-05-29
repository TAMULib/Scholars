import { ResourceView } from './';
import { Direction } from '../request';

export enum Layout {
    LIST = 'LIST',
    GRID = 'GRID'
}

export enum FacetType {
    STRING = 'STRING',
    DATE_TIME = 'DATE_TIME'
}

export enum FacetSort {
    COUNT = 'COUNT',
    INDEX = 'INDEX'
}

export interface Sort {
    readonly field: string;
    readonly direction: Direction;
    readonly date: boolean;
}

export interface Facet {
    readonly name: string;
    readonly field: string;
    readonly type: FacetType;
    readonly limit: number;
    readonly sort: FacetSort;
    readonly direction: Direction;
    readonly collapsed: boolean;
    readonly hidden: boolean;
}

export interface Filter {
    readonly field: string;
    readonly value: string;
}

export interface CollectionView extends ResourceView {
    readonly layout: Layout;
    readonly templates: any;
    templateFunctions?: any;
    readonly styles: string[];
    readonly facets: Facet[];
    readonly filters: Filter[];
    readonly sort: Sort[];
}
