import { DOCUMENT, isPlatformBrowser } from '@angular/common';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { Effect, ofType, Actions } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';

import { of, defer, EMPTY } from 'rxjs';
import { map, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../';
import { WindowDimensions } from '../layout/layout.reducer';

import { selectWindowDimensions } from '../layout';

import * as fromLayout from '../layout/layout.actions';
import * as fromSidebar from '../sidebar/sidebar.actions';

@Injectable()
export class LayoutEffects {

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        @Inject(DOCUMENT) private document: Document,
        private actions: Actions,
        private store: Store<AppState>
    ) {

    }

    @Effect() checkSidebarOnResize = this.actions.pipe(
        ofType(fromLayout.LayoutActionTypes.RESIZE_WINDOW),
        map((action: fromLayout.ResizeWindowAction) => action.payload.windowDimensions),
        map((windowDimensions: WindowDimensions) => this.checkSidebar(windowDimensions))
    );

    @Effect() checkSidebarOnLoad = this.actions.pipe(
        ofType(fromSidebar.SidebarActionTypes.LOAD_SIDEBAR),
        withLatestFrom(this.store.pipe(select(selectWindowDimensions))),
        map(([action, windowDimensions]) => this.checkSidebar(windowDimensions))
    );

    @Effect() initLayout = defer(() => {
        if (isPlatformBrowser(this.platformId)) {
            return of(new fromLayout.ResizeWindowAction({
                windowDimensions: {
                    width: window.innerWidth,
                    height: window.innerHeight
                }
            }));
        }
        return EMPTY;
    });


    private checkSidebar(windowDimensions: WindowDimensions): fromLayout.LayoutActions {
        return windowDimensions.width <= 991 ? new fromLayout.CloseSidebarAction() : new fromLayout.OpenSidebarAction();
    }

}
