import { Component, OnInit, Input } from '@angular/core';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

import { Observable } from 'rxjs';

import { SdrPage } from '../../core/model/sdr';

@Component({
    selector: 'scholars-pagination',
    templateUrl: 'pagination.component.html',
    styleUrls: ['pagination.component.scss']
})
export class PaginationComponent implements OnInit {

    @Input()
    public page: Observable<SdrPage>;

    public pageSizeOptions = [10, 25, 50, 100];

    constructor(private config: NgbPaginationConfig) {

    }

    ngOnInit() {
        this.config.maxSize = 5;
        this.config.rotate = true;
        this.config.ellipses = true;
        this.config.boundaryLinks = true;
    }

    public onPageChange(page: SdrPage): void {
        console.log(page);
    }

}
