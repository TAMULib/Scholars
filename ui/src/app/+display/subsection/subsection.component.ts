import { isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID, Input, AfterViewInit, OnInit, OnDestroy, ChangeDetectionStrategy } from '@angular/core';
import { Store, select } from '@ngrx/store';

import { Direction } from '../../core/model/request';
import { Filter, Sort } from '../../core/model/view';
import { SolrDocument } from '../../core/model/discovery';
import { Subsection } from '../../core/model/view/display-view';

import { AppState } from '../../core/store';

import { selectWindowDimensions } from '../../core/store/layout';
import { Subscription } from 'rxjs';
import { WindowDimensions } from '../../core/store/layout/layout.reducer';

@Component({
    selector: 'scholars-subsection',
    templateUrl: './subsection.component.html',
    styleUrls: ['./subsection.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class SubsectionComponent implements AfterViewInit, OnInit, OnDestroy {

    @Input()
    public subsection: Subsection;

    @Input()
    public document: SolrDocument;

    public page = 1;

    public pageSize = 5;

    public pageSizeOptions = [5, 10, 25, 50, 100, 500, 1000];

    public maxSize = 5;

    public rotate = true;

    public ellipses = true;

    public boundaryLinks = false;

    private subscriptions: Subscription[];

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private store: Store<AppState>
    ) {
        this.subscriptions = [];
    }

    ngAfterViewInit() {
        this.loadBadges();
    }

    ngOnInit() {
        this.pageSize = this.subsection.pageSize;
        this.subscriptions.push(this.store.pipe(select(selectWindowDimensions)).subscribe((windowDimensions: WindowDimensions) => {
            if (windowDimensions.width < 576) {
                this.maxSize = 3;
                this.ellipses = false;
                this.boundaryLinks = false;
            } else if (windowDimensions.width >= 576 && windowDimensions.width < 768) {
                this.maxSize = 3;
                this.ellipses = true;
                this.boundaryLinks = false;
            } else {
                this.maxSize = 5;
                this.ellipses = true;
                this.boundaryLinks = true;
            }
        }));
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    public getSubsectionCollection(resources: any[], filters: Filter[]): any[] {
        return resources.filter((r) => {
            for (const f of filters) {
                if ((Array.isArray(r[f.field]) ? r[f.field].indexOf(f.value) < 0 : r[f.field] !== f.value)) {
                    return false;
                }
            }
            return true;
        });
    }

    public getSubsectionResources(resources: any[], sort: Sort[]): any[] {
        let sorted = [].concat(resources);
        for (const s of sort) {
            const asc = Direction[s.direction] === Direction.ASC;
            sorted = sorted.sort((a, b) => {
                const av = s.date ? new Date(a[s.field]) : a[s.field];
                const bv = s.date ? new Date(b[s.field]) : b[s.field];
                return asc ? (av > bv) ? 1 : ((bv > av) ? -1 : 0) : (bv > av) ? 1 : ((av > bv) ? -1 : 0);
            });
        }
        return sorted;
    }

    public onPageChange(): void {
        setTimeout(() => this.loadBadges(), 250);
    }

    public onPageSizeSelect(pageSizeOption: number): void {
        this.pageSize = pageSizeOption;
        setTimeout(() => this.loadBadges(), 250);
    }

    private loadBadges(): void {
        if (isPlatformBrowser(this.platformId)) {
            setTimeout(() => {
                window['_altmetric_embed_init']();
                window['__dimensions_embed'].addBadges();
            }, 250);
        }
    }

}
