import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Component, Inject, OnInit, HostListener } from '@angular/core';
import { SafeStyle } from '@angular/platform-browser';
import { Store, select } from '@ngrx/store';

import { Observable } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

import { AppState } from './core/store';
import { AlertLocation } from './core/model/alert';
import { WindowDimensions } from './core/store/layout/layout.reducer';

import { selectStyle } from './core/store/theme';

import * as fromLayout from './core/store/layout/layout.actions';
import * as fromRouter from './core/store/router/router.actions';

@Component({
    selector: 'scholars-root',
    templateUrl: 'app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    public style: Observable<SafeStyle>;

    public location = AlertLocation.MAIN;

    private isPlatformBrowser: boolean;

    constructor(
        @Inject(PLATFORM_ID) platformId: string,
        private store: Store<AppState>
    ) {
        this.isPlatformBrowser = isPlatformBrowser(platformId);
    }

    ngOnInit(): void {
        this.style = this.store.pipe(
            select(selectStyle),
            skipWhile((style: SafeStyle) => style === undefined)
        );
    }

    @HostListener('click', ['$event'])
    public clickEvent(event): void {
        if (this.isPlatformBrowser && event.target.href && event.target.href.indexOf(event.target.baseURI) >= 0) {
            const path = event.target.href.replace(event.target.baseURI, '');
            if (path.length > 0 && path.indexOf('mailto:') < 0 && path.indexOf('tel:') < 0) {
                event.preventDefault();
                event.stopPropagation();
                this.store.dispatch(new fromRouter.Link({ url: path }));
            }
        }
    }

    @HostListener('window:resize', ['$event'])
    public onResize(event): void {
        this.dispatchResizeWindowAction({
            width: event.target.innerWidth,
            height: event.target.innerHeight
        } as WindowDimensions);
    }

    private dispatchResizeWindowAction(windowDimensions: WindowDimensions): void {
        this.store.dispatch(new fromLayout.ResizeWindowAction({ windowDimensions }));
    }

}
