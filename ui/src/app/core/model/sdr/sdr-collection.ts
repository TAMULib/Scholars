import { SdrPage } from './sdr-page';
import { SdrCollectionLinks } from './sdr-collection-links';

export interface SdrCollection {
    readonly page: SdrPage;
    readonly _embedded: any;
    readonly _links: SdrCollectionLinks;
}
