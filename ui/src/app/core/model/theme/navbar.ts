import { Link } from './link';
import { Style } from './style';

export interface Navbar {
    readonly brandText: string;
    readonly brandUri: string;
    readonly logoUri: string;
    readonly links: Link[];
    readonly variables: Style[];
}
