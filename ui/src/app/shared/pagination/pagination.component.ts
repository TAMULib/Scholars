import { Component, OnInit, Input, OnDestroy, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

import { Subscription, Observable } from 'rxjs';

import { SdrPage } from '../../core/model/sdr';
import { OperationKey, FacetSort } from '../../core/model/view';
import { Direction, Sort, Pageable, Indexable, Facet, SdrRequest } from '../../core/model/request';

@Component({
    selector: 'scholars-pagination',
    templateUrl: 'pagination.component.html',
    styleUrls: ['pagination.component.scss']
})
export class PaginationComponent implements OnInit, OnDestroy, OnChanges {

    @Input()
    public collection: string;

    @Input()
    public page: Observable<SdrPage>;

    @Output()
    public pageChange = new EventEmitter<SdrRequest>();

    public pageSizeOptions = [10, 25, 50, 100];

    private subscriptions: Subscription[];

    private request: SdrRequest;

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
            this.request = this.buildRequest(params);
            this.pageChange.emit(this.request);
        }));
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    ngOnChanges(changes: SimpleChanges) {
        if (this.request && changes.collection && changes.collection.currentValue !== changes.collection.previousValue) {
            this.request.collection = changes.collection.currentValue;
            this.pageChange.emit(this.request);
        }
    }

    public onPageChange(page): void {
        const urlTree = this.router.createUrlTree([], {
            queryParams: { page: page.number, size: page.size },
            queryParamsHandling: 'merge',
            preserveFragment: true
        });
        this.router.navigateByUrl(urlTree);
    }

    private buildRequest(params: Params): SdrRequest {
        const request = {
            collection: this.collection,
            pageable: this.buildPageable(params),
            facets: this.buildFacets(params),
            indexable: this.buildIndexable(params),
            query: params.query
        };
        return request;
    }

    private buildPageable(params: Params): Pageable {
        return {
            number: params.page,
            size: params.size,
            sort: this.buildSort(params.sort)
        };
    }

    private buildSort(sortParams: string): Sort[] {
        const sort: Sort[] = [];
        if (sortParams !== undefined) {
            if (Array.isArray(sortParams)) {
                sortParams.forEach((currentSortParam) => sort.push(this.splitSort(currentSortParam)));
            } else {
                sort.push(this.splitSort(sortParams));
            }
        }
        return sort;
    }

    private splitSort(sortParam: string): Sort {
        const sortSplit = sortParam.split(',');
        return {
            name: sortSplit[0],
            direction: Direction[sortSplit[1] !== undefined ? sortSplit[1].toUpperCase() : 'ASC']
        };
    }

    private buildFacets(params: Params): Facet[] {
        const facets: Facet[] = [];
        const fields: string[] = params.facets !== undefined ? params.facets.split(',') : [];
        fields.forEach((field: string) => {
            const facet: Facet = { field };
            ['limit', 'offset', 'sort', 'filter'].forEach((key: string) => {
                if (params[`${field}.${key}`]) {
                    facet[key] = key === 'sort' ? FacetSort[params[`${field}.${key}`]] : params[`${field}.${key}`];
                }
            });
            facets.push(facet);
        });
        return facets;
    }

    private buildIndexable(params: Params): Indexable {
        if (params.index) {
            const indexSplit: string[] = params.index.split(',');
            return {
                field: indexSplit[0],
                operationKey: OperationKey[indexSplit[1]],
                option: indexSplit[2]
            };
        }
    }

}
