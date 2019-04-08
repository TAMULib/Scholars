import { ResourceView, View } from './';

export interface DisplayTabSectionView {
    readonly name: string;
    readonly hidden: boolean;
    readonly template: string;
    templateFunction?: Function;
    readonly requiredFields: string[];
}

export interface DisplayTabView extends View {
    readonly hidden: boolean;
    readonly sections: DisplayTabSectionView[];
}

export interface DisplayView extends ResourceView {
    readonly mainContentTemplate: string;
    mainContentTemplateFunction?: Function;
    readonly leftScanTemplate: string;
    leftScanTemplateFunction?: Function;
    readonly rightScanTemplate: string;
    rightScanTemplateFunction?: Function;
    readonly tabs: DisplayTabView[];
}
