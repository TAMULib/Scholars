import { Navbar } from './navbar';
import { Banner } from './banner';
import { Style } from './style';

export interface Header {
    readonly navbar: Navbar;
    readonly banner: Banner;
    readonly variables: Style[];
}
