import { SdrResource } from '../sdr';
import { Style } from './style';
import { Home } from './home';
import { Header } from './header';
import { Footer } from './footer';

export interface Theme extends SdrResource {
    readonly name: string;
    readonly organization: string;
    readonly active: boolean;
    readonly home: Home;
    readonly header: Header;
    readonly footer: Footer;
    readonly colors: Style[];
    readonly variants: Style[];
    readonly variables: Style[];
}
