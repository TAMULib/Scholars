import { SdrRequest } from '../request';

export interface SdrPage extends SdrRequest {
    readonly totalElements: number;
    readonly totalPages: number;
}
