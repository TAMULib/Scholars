export interface Hero {
    readonly imageUri: string;
    readonly imageAlt: string;
    readonly watermarkImageUri: string;
    readonly watermarkText: string;
    readonly baseText: string;
    readonly fontColor: string;
    readonly linkColor: string;
    readonly linkHoverColor: string;
    readonly interval: number;
}
