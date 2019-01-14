import { Link } from './link';
import { Style } from './style';

export interface Footer {
    readonly links: Link[];
    readonly variables: Style[];
}
