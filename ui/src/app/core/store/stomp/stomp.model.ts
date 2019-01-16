export type Channel = Readonly<{
    id?: string;
    handle: (message: any) => void;
}>;
