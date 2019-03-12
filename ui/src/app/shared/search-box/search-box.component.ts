import { Component, Input, Inject, PLATFORM_ID, OnInit, OnDestroy } from '@angular/core';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router, Params, UrlTree } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { skipWhile, debounceTime, distinctUntilChanged, filter } from 'rxjs/operators';

import { AppState } from '../../core/store';
import { DiscoveryView, Facet, Filter } from '../../core/model/view';

import { selectActiveThemeOrganization } from '../../core/store/theme';
import { selectRouterSearchQuery } from '../../core/store/router';
import { selectAllResources } from '../../core/store/sdr';

export interface SearchBoxStyles {
    labelColor: string;
    inputBoxShadow: string;
}

@Component({
    selector: 'scholars-search-box',
    templateUrl: 'search-box.component.html',
    styleUrls: ['search-box.component.scss']
})
export class SearchBoxComponent implements OnInit, OnDestroy {

    @Input() styles: SearchBoxStyles = {
        labelColor: '#4d4d4d',
        inputBoxShadow: '1px 1px 0px 0px #bbb'
    };

    @Input() live = false;

    @Input() debounce = 750;

    public form: FormGroup;

    public organization: Observable<string>;

    public discoveryViews: Observable<DiscoveryView[]>;

    private subscriptions: Subscription[];

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private formBuilder: FormBuilder,
        private store: Store<AppState>,
        private router: Router
    ) {
        this.subscriptions = [];
    }

    ngOnInit(): void {
        this.form = this.formBuilder.group({
            query: new FormControl()
        });
        this.organization = this.store.pipe(select(selectActiveThemeOrganization));
        this.discoveryViews = this.store.pipe(
            select(selectAllResources<DiscoveryView>('discoveryViews')),
            filter((discoveryViews: DiscoveryView[]) => discoveryViews.length > 0)
        );

        this.subscriptions.push(this.store.pipe(
            select(selectRouterSearchQuery),
            skipWhile((query: string) => query === undefined)
        ).subscribe((query: string) => this.form.patchValue({ query })));

        if (this.live) {
            this.subscriptions.push(this.discoveryViews.subscribe((discoveryViews: DiscoveryView[]) => {
                this.subscriptions.push(this.form.controls.query.valueChanges.pipe(
                    debounceTime(this.debounce),
                    distinctUntilChanged()
                ).subscribe(() => this.onSearch(discoveryViews)));
            }));
        }
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    public isBrowserRendered(): boolean {
        return isPlatformBrowser(this.platformId);
    }

    public isServerRendered(): boolean {
        return isPlatformServer(this.platformId);
    }

    public onSearch(discoveryViews: DiscoveryView[]): void {
        const urlTree = this.buildUrlTree(discoveryViews[0]);
        this.router.navigateByUrl(urlTree);
    }

    public getAction(discoveryViews: DiscoveryView[]): string {
        const urlTree = this.buildUrlTree(discoveryViews[0]);
        return urlTree.toString();
    }

    private buildUrlTree(discoveryView: DiscoveryView): UrlTree {
        return this.router.createUrlTree(this.live ? [] : [`/discovery/${discoveryView.name}`], {
            queryParams: this.getSearchQueryParams(discoveryView),
            queryParamsHandling: this.live ? 'merge' : undefined,
            preserveFragment: true
        });
    }

    private getSearchQueryParams(discoveryView: DiscoveryView): Params {
        const queryParams: Params = {
            query: undefined,
            page: this.live ? 1 : undefined
        };
        if (discoveryView.facets && discoveryView.facets.length > 0) {
            let facets = '';
            discoveryView.facets.forEach((facet: Facet) => {
                facets += facets.length > 0 ? `,${facet.field}` : facet.field;
            });
            queryParams.facets = facets;
        }
        if (discoveryView.filters && discoveryView.filters.length > 0) {
            // tslint:disable-next-line:no-shadowed-variable
            discoveryView.filters.forEach((filter: Filter) => {
                queryParams[`${filter.field}.filter`] = filter.value;
            });
        }
        if (this.form.value.query && this.form.value.query.length > 0) {
            queryParams.query = this.form.value.query;
        }
        return queryParams;
    }

}
