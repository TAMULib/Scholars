import { Injectable } from '@angular/core';
import { Effect, ofType, Actions } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';

import { map, withLatestFrom } from 'rxjs/operators';

import { AppState } from '../';
import { WindowDimensions } from '../layout/layout.reducer';

import { selectIsMenuOpen } from './';
import { selectWindowDimensions } from '../layout';

import * as fromLayout from '../layout/layout.actions';
import * as fromSidebar from '../sidebar/sidebar.actions';

@Injectable()
export class SidebarEffects {

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

    @Effect() toggleSidebar = this.actions.pipe(
        ofType(fromLayout.LayoutActionTypes.TOGGLE_SIDEBAR),
        withLatestFrom(this.store.pipe(select(selectIsMenuOpen))),
        map(([action, menuOpen]) => menuOpen ? new fromSidebar.CloseSidebarAction() : new fromSidebar.OpenSidebarAction())
    );

    @Effect() checkSidebarOnLoad = this.actions.pipe(
        ofType(fromSidebar.SidebarActionTypes.LOAD_SIDEBAR),
        withLatestFrom(this.store.pipe(select(selectWindowDimensions))),
        map(([action, windowDimensions]) => this.checkSidebar(windowDimensions))
    );

    private checkSidebar(windowDimensions: WindowDimensions): fromSidebar.SidebarActions {
        return windowDimensions.width > 767 ? new fromSidebar.OpenSidebarAction() : new fromSidebar.CloseSidebarAction();
    }

}
