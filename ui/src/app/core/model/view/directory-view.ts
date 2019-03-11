import { CollectionView } from './';

// NOTE: support for some will require additional properties to be added to the Index and the subsequent request
export enum OperationKey {
    // BETWEEN = 'BETWEEN',
    CONTAINS = 'CONTAINS',
    ENDS_WITH = 'ENDS_WITH',
    // EQUALS = 'EQUALS',
    EXPRESSION = 'EXPRESSION',
    // FUNCTION = 'FUNCTION',
    FUZZY = 'FUZZY',
    // NEAR = 'NEAR',
    // SLOPPY = 'SLOPPY',
    STARTS_WITH = 'STARTS_WITH',
    // WITHIN = 'WITHIN',
}

export interface Index {
    readonly field: string;
    readonly operationKey: OperationKey;
    readonly options: string[];
}

export interface DirectoryView extends CollectionView {
    readonly index: Index;
}
