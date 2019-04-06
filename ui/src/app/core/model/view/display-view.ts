import { ResourceView, View } from './';

export interface DisplayTabSectionView {
    readonly name: string;
    readonly hidden: boolean;
    readonly template: string;
    readonly requiredFields: string[];
}

export interface DisplayTabView extends View {
    readonly hidden: boolean;
    readonly sections: DisplayTabSectionView[];
}

export interface DisplayView extends ResourceView {
    readonly mainContentTemplate: string;
    readonly leftScanTemplate: string;
    readonly rightScanTemplate: string;
    readonly tabs: DisplayTabView[];
}
