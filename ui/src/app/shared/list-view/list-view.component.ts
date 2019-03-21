import { Component, Input } from '@angular/core';

import { CollectionView } from '../../core/model/view';

@Component({
    selector: 'scholars-list-view',
    templateUrl: './list-view.component.html',
    styleUrls: ['./list-view.component.scss']
})
export class ListViewComponent {

    @Input()
    public view: CollectionView;

    @Input()
    public resources: any[];

}
