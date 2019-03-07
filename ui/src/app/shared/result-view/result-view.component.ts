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
    public resource: any;

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
            public resource: any;
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

        this.componentRef.instance.resource = this.resource;
    }

}
