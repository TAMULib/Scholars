import { ResourceView, View } from './';

export interface DisplaySection {
    readonly name: string;
    readonly hidden: boolean;
    readonly template: string;
    readonly requiredFields: string[];
}

export interface TabView extends View {
    readonly hidden: boolean;
    readonly sections: DisplaySection[];
}

export interface DisplayView extends ResourceView {
    readonly mainContentTemplate: string;
    readonly leftScanTemplate: string;
    readonly rightScanTemplate: string;
    readonly tabs: TabView[];
}
