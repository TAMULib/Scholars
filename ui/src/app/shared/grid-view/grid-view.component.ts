import { Component, Input } from '@angular/core';

import { CollectionView } from '../../core/model/view';

@Component({
    selector: 'scholars-grid-view',
    templateUrl: './grid-view.component.html',
    styleUrls: ['./grid-view.component.scss']
})
export class GridViewComponent {

    @Input()
    public view: CollectionView;

    @Input()
    public resources: any[];

}
