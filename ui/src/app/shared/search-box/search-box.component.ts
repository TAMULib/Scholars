import { Component, Input, Inject, PLATFORM_ID, OnInit, OnDestroy } from '@angular/core';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Store, select } from '@ngrx/store';

import { Observable, Subscription } from 'rxjs';
import { skipWhile, debounceTime, distinctUntilChanged } from 'rxjs/operators';

import { AppState } from '../../core/store';

import { selectActiveThemeOrganization } from '../../core/store/theme';
import { selectRouterSearchQuery } from '../../core/store/router';

import * as fromRouter from '../../core/store/router/router.actions';

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

    private subscriptions: Subscription[];

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private formBuilder: FormBuilder,
        private store: Store<AppState>
    ) {
        this.subscriptions = [];
    }

    ngOnInit(): void {
        this.form = this.formBuilder.group({
            query: new FormControl()
        });
        this.organization = this.store.pipe(select(selectActiveThemeOrganization));
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
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    public onSearch(): void {
        let navigation: fromRouter.RouterNavigation = {
            path: ['/search']
        };
        if (this.form.value.query && this.form.value.query.length > 0) {
            navigation = Object.assign(navigation, { query: { query: this.form.value.query } });
        }
        this.store.dispatch(new fromRouter.Go(navigation));
    }

    public isBrowserRendered(): boolean {
        return isPlatformBrowser(this.platformId);
    }

    public isServerRendered(): boolean {
        return isPlatformServer(this.platformId);
    }

}
