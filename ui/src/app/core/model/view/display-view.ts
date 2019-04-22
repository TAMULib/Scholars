import { View } from './';

export interface LazyReference {
    readonly field: string;
    readonly collection: string;
}

export interface DisplayTabSectionView extends View {
    readonly hidden: boolean;
    readonly template: string;
    templateFunction?: Function;
    readonly requiredFields: string[];
    readonly lazyReferences: LazyReference[];
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
    readonly metaTemplates: any;
    metaTemplateFunctions?: any;
    readonly tabs: DisplayTabView[];
}
