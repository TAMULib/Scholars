import { Injectable } from '@angular/core';
import { Effect, ofType, Actions } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';

import { map, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../';
import { WindowDimensions } from '../layout/layout.reducer';

import { selectWindowDimensions } from '../layout';

import * as fromLayout from '../layout/layout.actions';
import * as fromSidebar from '../sidebar/sidebar.actions';

@Injectable()
export class LayoutEffects {

    constructor(
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

    private checkSidebar(windowDimensions: WindowDimensions): fromLayout.LayoutActions {
        return windowDimensions.width <= 767 ? new fromLayout.CloseSidebarAction() : new fromLayout.OpenSidebarAction();
    }

}
