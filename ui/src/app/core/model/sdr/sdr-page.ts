export interface SdrPageRequest {
    readonly number: number;
    readonly size: number;
}

export interface SdrPage extends SdrPageRequest {
    readonly totalElements: number;
    readonly totalPages: number;
}
