import { CollectionView } from './';

export enum Type {
    BETWEEN,
    IS_NOT_NULL,
    IS_NULL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    BEFORE,
    AFTER,
    NOT_LIKE,
    LIKE,
    STARTING_WITH,
    ENDING_WITH,
    IS_NOT_EMPTY,
    IS_EMPTY,
    NOT_CONTAINING,
    CONTAINING,
    NOT_IN,
    IN,
    NEAR,
    WITHIN,
    REGEX,
    EXISTS,
    TRUE,
    FALSE,
    NEGATING_SIMPLE_PROPERTY,
    SIMPLE_PROPERTY
}

export interface Index {
    readonly type: Type;
    readonly field: string;
}

export interface DirectoryView extends CollectionView {
    readonly index: Index;
}
