import { View } from './';

export interface LazyReference {
    readonly field: string;
    readonly collection: string;
}

export interface DisplayTabSectionView extends View {
    readonly hidden: boolean;
    readonly template: string;
    templateFunction?: (document: any) => string;
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
    mainContentTemplateFunction?: (document: any) => string;
    readonly leftScanTemplate: string;
    leftScanTemplateFunction?: (document: any) => string;
    readonly rightScanTemplate: string;
    rightScanTemplateFunction?: (document: any) => string;
    readonly metaTemplates: any;
    metaTemplateFunctions?: any;
    readonly tabs: DisplayTabView[];
}
