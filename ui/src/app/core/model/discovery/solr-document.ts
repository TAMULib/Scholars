import { SdrResource } from '../sdr';

export interface SolrDocument extends SdrResource {
    readonly id: number;
    readonly type: string[];
}
