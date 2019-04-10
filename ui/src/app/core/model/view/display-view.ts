import { View } from './';

export interface DisplayTabSectionView extends View {
    readonly hidden: boolean;
    readonly template: string;
    templateFunction?: Function;
    readonly requiredFields: string[];
}

export interface DisplayTabView extends View {
    readonly hidden: boolean;
    readonly sections: DisplayTabSectionView[];
}

export interface DisplayView extends View {
    readonly types: string[];
    readonly mainContentTemplate: string;
    mainContentTemplateFunction?: Function;
    readonly leftScanTemplate: string;
    leftScanTemplateFunction?: Function;
    readonly rightScanTemplate: string;
    rightScanTemplateFunction?: Function;
    readonly tabs: DisplayTabView[];
}
