import { Params } from '@angular/router';

import { Facet, Filter, CollectionView } from '../../core/model/view';

const addFacetsToQueryParams = (queryParams: Params, collectionView: CollectionView): void => {
    if (collectionView.facets && collectionView.facets.length > 0) {
        let facets = '';
        collectionView.facets.forEach((facet: Facet) => {
            facets += facets.length > 0 ? `,${facet.field}` : facet.field;
        });
        queryParams.facets = facets;
    }
};

const addFiltersToQueryParams = (queryParams: Params, collectionView: CollectionView): void => {
    if (collectionView.filters && collectionView.filters.length > 0) {
        collectionView.filters.forEach((filter: Filter) => {
            queryParams[`${filter.field}.filter`] = filter.value;
        });
    }
};

const addSortToQueryParams = (queryParams: Params, collectionView: CollectionView): void => {
    // NOTE: only first sort is applied to query
    // Spring requires multiple sort parameters use multiple entries with the 'sort' key
    // e.g. ?sort=name,asc&sort=preferredTitle,desc
    // Angular unfortunately does not support constructing that with queryParams
    if (collectionView.sort && collectionView.sort.length > 0) {
        queryParams.sort = `${collectionView.sort[0].field},${collectionView.sort[0].direction}`;
    }
};

export {
    addFacetsToQueryParams,
    addFiltersToQueryParams,
    addSortToQueryParams
};
