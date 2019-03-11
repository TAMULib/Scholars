import { Component, OnInit, Input } from '@angular/core';

import { CollectionView } from '../../core/model/view';
import { ResultViewService } from '../../core/service/result-view.service';

@Component({
    selector: 'scholars-result-view',
    templateUrl: './result-view.component.html',
    styleUrls: ['./result-view.component.scss']
})
export class ResultViewComponent implements OnInit {

    @Input()
    public view: CollectionView;

    @Input()
    public resource: any;

    public resultHtml: string;

    constructor(private resultViewService: ResultViewService) {

    }

    ngOnInit() {
        this.resultHtml = this.resultViewService.compileResultView(this.view, this.resource);
    }

}
