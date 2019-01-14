import { Hero } from './hero';
import { Style } from './style';

export interface Home {
    readonly heroesNavigable: boolean;
    readonly heroes: Hero[];
    readonly variables: Style[];
}
