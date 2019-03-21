import { SdrFacet } from './sdr-facet';
import { SdrPage } from './sdr-page';
import { SdrCollectionLinks } from './sdr-collection-links';

export interface SdrCollection {
    readonly page: SdrPage;
    readonly facets?: SdrFacet[];
    readonly _embedded: any;
    readonly _links: SdrCollectionLinks;
}
