import { Role } from './role';
import { SdrResource } from '../sdr';

export interface User extends SdrResource {
    readonly firstName: string;
    readonly lastName: string;
    readonly email: string;
    readonly role: Role;
    readonly active: boolean;
    readonly enabled: boolean;
}
