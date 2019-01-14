export enum AlertLocation {
    MAIN = 'main',
    DIALOG = 'dialog',
}

export enum AlertType {
    PRIMARY = 'primary',
    SECONDARY = 'secondary',
    SUCCESS = 'success',
    DANGER = 'danger',
    WARNING = 'warning',
    INFO = 'info',
    LIGHT = 'light',
    DARK = 'dark'
}

export type Alert = Readonly<{
    location: AlertLocation;
    type: AlertType;
    message: string;
    dismissible?: boolean;
    timer?: number;
    index?: number;
}>;
