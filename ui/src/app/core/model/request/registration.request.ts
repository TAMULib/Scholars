export interface RegistrationRequest {
    firstName: string;
    lastName: string;
    email: string;
    password?: string;
    confirm?: string;
}
