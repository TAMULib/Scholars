import { SdrField } from './sdr-field';

export interface SdrFacetContent {
    readonly field: SdrField;
    readonly key: SdrField;
    readonly value: string;
    readonly valueCount: number;
}
