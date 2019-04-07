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
import * as fromMetadata from './core/store/metadata/metadata.actions';
import * as fromRouter from './core/store/router/router.actions';

@Component({
    selector: 'scholars-root',
    templateUrl: 'app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    private isPlatformBrowser: boolean;

    public style: Observable<SafeStyle>;

    public location = AlertLocation.MAIN;

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
        this.store.dispatch(new fromMetadata.AddMetadataTagsAction({
            tags: [{
                name: 'title', content: 'Scholars'
            }]
        }));
    }

    @HostListener('click', ['$event'])
    public clickEvent(event): void {
        if (this.isPlatformBrowser && event.target.href) {
            const path = event.target.href.replace(event.target.baseURI, '');
            this.store.dispatch(new fromRouter.Link({ url: path }));
            event.preventDefault();
            event.stopPropagation();
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
