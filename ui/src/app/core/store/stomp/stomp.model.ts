export type StompSubscription = Readonly<{
    id: string;
    unsubscribe: Function;
}>;
