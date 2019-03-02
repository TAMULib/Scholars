import { SdrFacetContent } from './sdr-facet-content';
import { SdrFacetQueryResult } from './sdr-facet-query-result';
import { SdrPageable } from './sdr-pageable';
import { SdrSort } from './sdr-sort';

export interface SdrFacet {
    readonly allFacets: any[];
    readonly alternatives: any[];
    readonly content: SdrFacetContent[];
    readonly empty: boolean;
    readonly facetFields: any[];
    readonly facetPivotFields: any[];
    readonly facetQueryResults: SdrFacetQueryResult;
    readonly facetResultPages: any[];
    readonly fieldStatsResults: any;
    readonly first: boolean;
    readonly highlighted: any[];
    readonly last: boolean;
    readonly maxScore: any;
    readonly number: number;
    readonly numberOfelements: number;
    readonly pageable: SdrPageable;
    readonly size: number;
    readonly sort: SdrSort;
    readonly suggestions: any[];
    readonly totalElements: number;
    readonly totalPages: number;
}
