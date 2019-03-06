import {
    Component,
    ViewChild,
    OnDestroy,
    AfterContentInit,
    Input,
    Compiler,
    ViewContainerRef,
    NgModule,
    ComponentRef
} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { SolrDocument } from '../../core/model/discovery';
import { CollectionView } from '../../core/model/view';

@Component({
    selector: 'scholars-result-view',
    templateUrl: './result-view.component.html',
    styleUrls: ['./result-view.component.scss']
})
export class ResultViewComponent implements AfterContentInit, OnDestroy {

    @Input()
    public view: CollectionView;

    @Input()
    public document: SolrDocument;

    @ViewChild('dynamicResultView', { read: ViewContainerRef })
    public dynamicResultView: ViewContainerRef;

    public componentRef: ComponentRef<any>;

    constructor(private compiler: Compiler) {

    }

    ngOnDestroy() {
        if (this.componentRef) {
            this.componentRef.destroy();
        }
    }

    ngAfterContentInit() {
        this.renderResultView(this.view.template, this.view.styles);
    }

    private renderResultView(template: string, styles: string[] = []) {

        @Component({
            template: template,
            styles
        })
        class DynamicComponent {
            public document: SolrDocument;
            constructor() { }
        }

        @NgModule({
            imports: [
                BrowserModule
            ],
            declarations: [
                DynamicComponent
            ]
        })
        class DynamicComponentModule { }

        const dynamicComponentModule = this.compiler.compileModuleAndAllComponentsSync(DynamicComponentModule);

        const factory = dynamicComponentModule.componentFactories.find((component) =>
            component.componentType === DynamicComponent
        );

        this.componentRef = this.dynamicResultView.createComponent(factory);

        this.componentRef.instance.document = this.document;
    }

}
