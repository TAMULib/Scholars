import { SdrLink } from './sdr-link';

export interface SdrCollectionLinks {
    readonly self: SdrLink;
    readonly profile?: SdrLink;
    readonly search?: SdrLink;
}
