import { AlertLocation } from './';
import { AlertType } from './';

export type Alert = Readonly<{
    location: AlertLocation;
    type: AlertType;
    message: string;
    dismissible?: boolean;
    timer?: number;
    index?: number;
}>;
