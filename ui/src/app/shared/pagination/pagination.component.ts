import { Component, Input } from '@angular/core';
import { Params } from '@angular/router';

import { Observable } from 'rxjs';

import { SdrPage } from '../../core/model/sdr';

@Component({
    selector: 'scholars-pagination',
    templateUrl: 'pagination.component.html',
    styleUrls: ['pagination.component.scss']
})
export class PaginationComponent {

    @Input()
    public page: Observable<SdrPage>;

    @Input()
    public size: 'sm' | 'lg';

    @Input()
    public maxSize = 5;

    @Input()
    public pageSizeOptions = [10, 25, 50, 100];

    public getPages(page: SdrPage): number[] {

        let pages: number[] = [];

        for (let i = 1; i <= page.totalPages; i++) {
            pages.push(i);
        }

        // apply this.maxSize if necessary
        if (this.maxSize > 0 && page.totalPages > this.maxSize) {
            let start = 0;
            let end = page.totalPages;

            const leftOffset = Math.floor(this.maxSize / 2);
            const rightOffset = this.maxSize % 2 === 0 ? leftOffset - 1 : leftOffset;

            if (page.number <= leftOffset) {
                // very beginning, no rotation -> [0..this.maxSize]
                end = this.maxSize;
            } else if (page.totalPages - page.number < leftOffset) {
                // very end, no rotation -> [len-this.maxSize..len]
                start = page.totalPages - this.maxSize;
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

    public getRouterLink(): string[] {
        return [];
    }

    public getQueryParams(page: number, size: number): Params {
        return { page, size };
    }

}
