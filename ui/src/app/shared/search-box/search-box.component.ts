import { Component, Input, Inject, PLATFORM_ID, OnInit, OnDestroy } from '@angular/core';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router, Params, UrlTree } from '@angular/router';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { skipWhile, debounceTime, distinctUntilChanged } from 'rxjs/operators';

import { AppState } from '../../core/store';
import { DiscoveryView, Facet, Filter } from '../../core/model/view';

import { selectActiveThemeOrganization } from '../../core/store/theme';
import { selectRouterSearchQuery } from '../../core/store/router';

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

    @Input() view: DiscoveryView;

    @Input() styles: SearchBoxStyles = {
        labelColor: '#4d4d4d',
        inputBoxShadow: '1px 1px 0px 0px #bbb'
    };

    @Input() live = false;

    @Input() debounce = 750;

    public form: FormGroup;

    public organization: Observable<string>;

    private subscriptions: Subscription[];

    private setup = false;

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private formBuilder: FormBuilder,
        private store: Store<AppState>,
        private router: Router
    ) {
        this.subscriptions = [];
    }

    ngOnInit(): void {
        this.organization = this.store.pipe(select(selectActiveThemeOrganization));
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    public setupForm(): boolean {
        if (!this.setup) {

            const formGroup = {
                query: new FormControl(),
                collection: new FormControl(),
                facets: new FormControl()
            };

            if (this.view.filters && this.view.filters.length > 0) {
                this.view.filters.forEach((filter: Filter) => {
                    formGroup[`${filter.field}.filter`] = new FormControl();
                });
            }

            this.form = this.formBuilder.group(formGroup);

            if (this.view.facets && this.view.facets.length > 0) {
                let facets = '';
                this.view.facets.forEach((facet: Facet) => {
                    facets += facets.length > 0 ? `,${facet.field}` : facet.field;
                });
                this.form.patchValue({ facets });
            }

            if (this.view.filters && this.view.filters.length > 0) {
                // tslint:disable-next-line:no-shadowed-variable
                this.view.filters.forEach((filter: Filter) => {
                    const field = {};
                    field[`${filter.field}.filter`] = filter.value;
                    this.form.patchValue(field);
                });
            }

            const collection = this.view.collection;

            this.form.patchValue({ collection });

            this.subscriptions.push(this.store.pipe(
                select(selectRouterSearchQuery),
                skipWhile((query: string) => query === undefined)
            ).subscribe((query: string) => this.form.patchValue({ query })));

            if (this.live) {
                this.subscriptions.push(this.form.controls.query.valueChanges.pipe(
                    debounceTime(this.debounce),
                    distinctUntilChanged()
                ).subscribe(() => this.onSearch()));
            }

            this.setup = true;
        }
        return this.setup;
    }

    public isBrowserRendered(): boolean {
        return isPlatformBrowser(this.platformId);
    }

    public isServerRendered(): boolean {
        return isPlatformServer(this.platformId);
    }

    public onSearch(): void {
        console.log('here');
        const queryParams: Params = this.getDiscoveryQueryParams(this.form.value.query);
        const urlTree = this.buildUrlTree(queryParams);
        this.router.navigateByUrl(urlTree);
    }

    public getAction(): string {
        return `/discovery/${this.view.name}`;
    }

    public getFilterName(filter: Filter): string {
        return `${filter.field}.filter`;
    }

    // NOTE: redundant with getDiscoveryQueryParams from DiscoveryComponent
    private getDiscoveryQueryParams(query: string): Params {
        const queryParams: Params = {};
        queryParams.collection = this.view.collection;
        if (this.view.facets && this.view.facets.length > 0) {
            let facets = '';
            this.view.facets.forEach((facet: Facet) => {
                facets += facets.length > 0 ? `,${facet.field}` : facet.field;
            });
            queryParams.facets = facets;
        }
        if (this.view.filters && this.view.filters.length > 0) {
            // tslint:disable-next-line:no-shadowed-variable
            this.view.filters.forEach((filter: Filter) => {
                queryParams[`${filter.field}.filter`] = filter.value;
            });
        }
        if (query && query.length > 0) {
            queryParams.query = query;
        } else {
            queryParams.query = undefined;
        }
        queryParams.page = this.live ? 1 : undefined;
        return queryParams;
    }

    private buildUrlTree(queryParams: Params): UrlTree {
        return this.router.createUrlTree([`/discovery/${this.view.name}`], {
            queryParams: queryParams,
            queryParamsHandling: this.live ? 'merge' : undefined,
            preserveFragment: true
        });
    }

}
