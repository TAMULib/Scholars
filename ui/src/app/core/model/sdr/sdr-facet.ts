import { SdrFacetEntry } from './sdr-facet-entry';
import { SdrPage } from './sdr-page';

export interface SdrFacet {
    readonly field: string;
    readonly entries: SdrFacetEntry[];
    readonly page: SdrPage;
}
