import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { ActivatedRoute, Params } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

import { Store, select } from '@ngrx/store';

import { Subscription, Observable, BehaviorSubject } from 'rxjs';
import { filter } from 'rxjs/operators';

import { AppState } from '../../core/store';
import { Home, Hero } from '../../core/model/theme';
import { DiscoveryView } from '../../core/model/view';

import { SearchBoxStyles } from '../../shared/search-box/search-box.component';

import { selectDefaultDiscoveryView } from '../../core/store/sdr';
import { selectActiveThemeHome, selectActiveThemeOrganization } from '../../core/store/theme';

import * as fromAuth from '../../core/store/auth/auth.actions';

@Component({
    selector: 'scholars-home',
    templateUrl: 'home.component.html',
    styleUrls: ['home.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit, OnDestroy {

    public discoveryView: Observable<DiscoveryView>;

    public home: Observable<Home>;

    public organization: Observable<string>;

    public searchStyles: BehaviorSubject<SearchBoxStyles>;

    private subscriptions: Subscription[];

    constructor(
        public config: NgbCarouselConfig,
        private sanitizer: DomSanitizer,
        private route: ActivatedRoute,
        private store: Store<AppState>
    ) {
        this.subscriptions = [];
    }

    ngOnInit() {
        this.discoveryView = this.store.pipe(
            select(selectDefaultDiscoveryView),
            filter((view: DiscoveryView) => view !== undefined)
        );
        this.home = this.store.pipe(
            select(selectActiveThemeHome),
            filter((home: Home) => home !== undefined)
        );
        this.organization = this.store.pipe(
            select(selectActiveThemeOrganization),
            filter((organization: string) => organization !== undefined)
        );
        this.subscriptions.push(this.route.queryParams.subscribe((params: Params) => {
            if (params.key !== undefined) {
                this.store.dispatch(new fromAuth.ConfirmRegistrationAction({ key: params.key }));
            }
        }));
        this.searchStyles = new BehaviorSubject<SearchBoxStyles>(undefined);
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    public showCarousel(home: Home): boolean {
        this.config.showNavigationIndicators = false;
        this.config.showNavigationArrows = home.heroesNavigable;
        return home.heroes.length > 0;
    }

    public getSearchStyles(): Observable<SearchBoxStyles> {
        return this.searchStyles.asObservable();
    }

    public getCarouselBackgroundImage(hero: Hero): any {
        this.config.interval = hero.interval;
        this.searchStyles.next({
            label: {
                margin: '0px 0px 30px 0px',
                color: hero.fontColor
            },
            inputBoxShadow: 'inset 1px 1px 0px 0px #bbb'
        });
        return {
            'background-image': `linear-gradient(rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.5)), url('${hero.imageUri}')`
        };
    }

    public getWatermarkBackgroundImage(hero: Hero): any {
        return {
            'background-image': `url('${hero.watermarkImageUri}')`
        };
    }

    public getStyleVariables(hero: Hero): SafeStyle {
        const variables = `--hero-color:${hero.fontColor};--hero-link-color:${hero.linkColor};--hero-link-hover-color:${hero.linkHoverColor}`;
        return this.sanitizer.bypassSecurityTrustStyle(variables);
    }

}
