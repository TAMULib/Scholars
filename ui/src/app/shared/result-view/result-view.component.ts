import { Component, Input } from '@angular/core';

import { SolrDocument } from '../../core/model/discovery';
import { ResultView } from '../../core/model/view';

@Component({
    selector: 'scholars-result-view',
    templateUrl: './result-view.component.html',
    styleUrls: ['./result-view.component.scss']
})
export class ResultViewComponent {

    @Input()
    public resultView: ResultView;

    @Input()
    public document: SolrDocument;

    constructor() {

    }

}
