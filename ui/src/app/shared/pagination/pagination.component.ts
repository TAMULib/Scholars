import { Component, Input, OnInit } from '@angular/core';
import { Params, Router, ActivatedRoute } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';

import { AppState } from '../../core/store';

import { SdrPage } from '../../core/model/sdr';
import { WindowDimensions } from '../../core/store/layout/layout.reducer';

import { selectWindowDimensions } from '../../core/store/layout';

@Component({
    selector: 'scholars-pagination',
    templateUrl: 'pagination.component.html',
    styleUrls: ['pagination.component.scss']
})
export class PaginationComponent implements OnInit {

    @Input()
    public page: Observable<SdrPage>;

    @Input()
    public size: 'sm' | 'lg';

    @Input()
    public queryPrefix: string;

    @Input()
    public pageSizeOptions = [10, 25, 50, 100];

    @Input()
    public pageSizeOptionsType: 'list' | 'dropdown' = 'dropdown';

    public windowDimensions: Observable<WindowDimensions>;

    constructor(
        private store: Store<AppState>,
        private router: Router,
        private route: ActivatedRoute
    ) {

    }

    ngOnInit() {
        this.windowDimensions = this.store.pipe(select(selectWindowDimensions));
    }

    public getPages(page: SdrPage, windowDimensions: WindowDimensions): number[] {

        let pages: number[] = [];

        const maxSize = windowDimensions.width < 576 ? 1 : windowDimensions.width < 768 ? 3 : 5;

        for (let i = 1; i <= page.totalPages; i++) {
            pages.push(i);
        }

        // apply this.maxSize if necessary
        if (maxSize > 0 && page.totalPages > maxSize) {
            let start = 0;
            let end = page.totalPages;

            const leftOffset = Math.floor(maxSize / 2);
            const rightOffset = maxSize % 2 === 0 ? leftOffset - 1 : leftOffset;

            if (page.number <= leftOffset) {
                // very beginning, no rotation -> [0..this.maxSize]
                end = maxSize;
            } else if (page.totalPages - page.number < leftOffset) {
                // very end, no rotation -> [len-this.maxSize..len]
                start = page.totalPages - maxSize;
            } else {
                // rotate
                start = page.number - leftOffset - 1;
                end = page.number + rightOffset;
            }

            pages = pages.slice(start, end);

            if (start > 0) {
                if (start > 1) {
                    pages.unshift(-1);
                }
                pages.unshift(1);
            }
            if (end < page.totalPages) {
                if (end < (page.totalPages - 1)) {
                    pages.push(-1);
                }
                pages.push(page.totalPages);
            }
        }

        return pages;
    }

    public hasPrevious(pageNumber: number): boolean { return pageNumber > 1; }

    public hasNext(pageNumber: number, totalPages: number): boolean { return pageNumber < totalPages; }

    public nextDisabled(pageNumber: number, totalPages: number): boolean { return !this.hasNext(pageNumber, totalPages); }

    public previousDisabled(pageNumber: number): boolean { return !this.hasPrevious(pageNumber); }

    public isEllipsis(pageNumber: number): boolean { return pageNumber === -1; }

    public buildUrl(page: number, size: number): string {
        const params: Params = {};
        params[this.queryPrefix && this.queryPrefix.length > 0 ? `${this.queryPrefix}.page` : 'page'] = page;
        params[this.queryPrefix && this.queryPrefix.length > 0 ? `${this.queryPrefix}.size` : 'size'] = size;
        const urlTree = this.router.createUrlTree(['.'], {
            relativeTo: this.route,
            queryParams: params,
            queryParamsHandling: 'merge'
        });
        return this.router.serializeUrl(urlTree);
    }

}
