import { Component, Input } from '@angular/core';

import { DisplayTabSectionView } from '../../core/model/view';
import { SolrDocument } from '../../core/model/discovery';

@Component({
    selector: 'scholars-section',
    templateUrl: './section.component.html',
    styleUrls: ['./section.component.scss']
})
export class SectionComponent {

    @Input()
    public section: DisplayTabSectionView;

    @Input()
    public document: SolrDocument;

}
