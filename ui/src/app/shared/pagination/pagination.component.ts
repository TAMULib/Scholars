import { Component, OnInit, Input, OnDestroy, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

import { Subscription, Observable } from 'rxjs';

import { SdrPage, SdrPageRequest } from '../../core/model/sdr';

@Component({
    selector: 'scholars-pagination',
    templateUrl: 'pagination.component.html',
    styleUrls: ['pagination.component.scss']
})
export class PaginationComponent implements OnInit, OnDestroy {

    @Input() page: Observable<SdrPage>;

    @Output() pageChange = new EventEmitter<SdrPageRequest>();

    public pageSizeOptions = [10, 25, 50, 100];

    private subscriptions: Subscription[];

    constructor(
        public config: NgbPaginationConfig,
        private route: ActivatedRoute,
        private router: Router
    ) {
        this.subscriptions = [];
    }

    ngOnInit() {
        this.config.maxSize = 5;
        this.config.rotate = true;
        this.config.ellipses = true;
        this.config.boundaryLinks = true;
        this.subscriptions.push(this.route.queryParams.subscribe((params: Params) => {
            const pageNumber = params.page !== undefined ? params.page : 1;
            const pageSize = params.size !== undefined ? params.size : 10;
            this.pageChange.emit({
                number: pageNumber,
                size: pageSize
            });
        }));
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    public onPageChange(page): void {
        const urlTree = this.router.createUrlTree([], {
            queryParams: { page: page.number, size: page.size },
            queryParamsHandling: 'merge',
            preserveFragment: true
        });
        this.router.navigateByUrl(urlTree);
    }

}
