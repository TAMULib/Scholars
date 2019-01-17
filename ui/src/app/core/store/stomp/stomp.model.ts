export type StompChannel = Readonly<{
    id?: string;
    handle: (message: any) => void;
}>;

export type StompSubscription = Readonly<{
    id: string;
    unsubscribe: () => void
}>;
