import { Style } from './style';

export interface Banner {
    readonly imageUri: string;
    readonly altText: string;
    readonly variables: Style[];
}
