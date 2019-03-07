import {
    Component,
    ViewChild,
    OnDestroy,
    AfterContentInit,
    Input,
    ViewContainerRef,
    ComponentRef
} from '@angular/core';

import { CollectionView } from '../../core/model/view';
import { ResultViewService } from '../../core/service/result-view.service';

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

    constructor(private resultViewService: ResultViewService) {

    }

    ngOnDestroy() {
        if (this.componentRef) {
            this.componentRef.destroy();
        }
    }

    ngAfterContentInit() {
        const factory = this.resultViewService.compileDynamicResultView(this.view);
        this.componentRef = this.dynamicResultView.createComponent(factory);
        this.componentRef.instance.resource = this.resource;
    }

}
