import { Component, Input } from '@angular/core';

import { DisplayTabView, DisplayTabSectionView } from '../../core/model/view';
import { SolrDocument } from '../../core/model/discovery';

import { sectionsToShow } from '../display.component';

@Component({
    selector: 'scholars-tab',
    templateUrl: './tab.component.html',
    styleUrls: ['./tab.component.scss']
})
export class TabComponent {

    @Input()
    public tab: DisplayTabView;

    @Input()
    public document: SolrDocument;

    public getSectionsToShow(sections: DisplayTabSectionView[], document: SolrDocument): DisplayTabSectionView[] {
        return sectionsToShow(sections, document);
    }

}
