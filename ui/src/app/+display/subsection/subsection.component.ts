import { isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID, Input, AfterViewInit, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Params, Router, NavigationStart } from '@angular/router';

import { Subscription, BehaviorSubject, Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Direction } from '../../core/model/request';
import { Filter, Sort } from '../../core/model/view';
import { SolrDocument } from '../../core/model/discovery';
import { Subsection } from '../../core/model/view/display-view';
import { SdrPage } from '../../core/model/sdr';

@Component({
    selector: 'scholars-subsection',
    templateUrl: './subsection.component.html',
    styleUrls: ['./subsection.component.scss']
})
export class SubsectionComponent implements AfterViewInit, OnInit, OnDestroy {

    @Input()
    public subsection: Subsection;

    @Input()
    public document: SolrDocument;

    public resources: BehaviorSubject<any[]>;

    public page: Observable<SdrPage>;

    public pageSizeOptions = [5, 10, 25, 50, 100, 500, 1000];

    private subscriptions: Subscription[];

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private router: Router,
        private route: ActivatedRoute
    ) {
        this.resources = new BehaviorSubject<any[]>([]);
        this.subscriptions = [];
    }

    ngAfterViewInit() {
        this.loadBadges();
    }

    ngOnInit() {
        this.subscriptions.push(this.router.events.pipe(
            filter(event => event instanceof NavigationStart)
        ).subscribe(() => {
            this.loadBadges();
        }));
        const resources = this.getSubsectionCollection(this.document[this.subsection.field], this.subsection.filters);
        this.page = this.route.queryParams.pipe(
            map((params: Params) => {
                const pageSize = params[`${this.subsection.name}.size`] ? Number(params[`${this.subsection.name}.size`]) : this.subsection.pageSize;
                const pageNumber = params[`${this.subsection.name}.page`] ? Number(params[`${this.subsection.name}.page`]) : 1;
                return {
                    size: pageSize,
                    totalElements: this.resources.getValue().length,
                    totalPages: Math.ceil(this.resources.getValue().length / this.subsection.pageSize),
                    number: pageNumber,
                };
            })
        );
        this.resources.next(resources);
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    public getResources(): Observable<any[]> {
        return this.resources.asObservable();
    }

    public getResourcesPage(resources: any[], sort: Sort[], page: SdrPage): any[] {
        let sorted = [].concat(resources);
        for (const s of sort) {
            const asc = Direction[s.direction] === Direction.ASC;
            sorted = sorted.sort((a, b) => {
                const av = s.date ? new Date(a[s.field]) : a[s.field];
                const bv = s.date ? new Date(b[s.field]) : b[s.field];
                return asc ? (av > bv) ? 1 : ((bv > av) ? -1 : 0) : (bv > av) ? 1 : ((av > bv) ? -1 : 0);
            });
        }
        const pageStart = (page.number - 1);
        return sorted.splice(pageStart, pageStart + page.size);
    }

    private loadBadges(): void {
        if (isPlatformBrowser(this.platformId)) {
            setTimeout(() => {
                window['_altmetric_embed_init']();
                window['__dimensions_embed'].addBadges();
            }, 250);
        }
    }

    private getSubsectionCollection(resources: any[], filters: Filter[]): any[] {
        return resources.filter((r) => {
            for (const f of filters) {
                if ((Array.isArray(r[f.field]) ? r[f.field].indexOf(f.value) < 0 : r[f.field] !== f.value)) {
                    return false;
                }
            }
            return true;
        });
    }

}
