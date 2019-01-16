import { InjectionToken } from '@angular/core';

import { ActionReducerMap, MetaReducer } from '@ngrx/store';

import * as fromRouter from '@ngrx/router-store';

import * as fromAlert from './alert/alert.reducer';
import * as fromAuth from './auth/auth.reducer';
import * as fromDialog from './dialog/dialog.reducer';
import * as fromLayout from './layout/layout.reducer';
import * as fromMetadata from './metadata/metadata.reducer';
import * as fromSidebar from './sidebar/sidebar.reducer';
import * as fromStomp from './stomp/stomp.reducer';
import * as fromThemes from './themes/themes.reducer';
import * as fromUsers from './users/users.reducer';
import * as fromRootStore from './root-store.reducer';

export interface AppState {
  alert: fromAlert.AlertState;
  auth: fromAuth.AuthState;
  dialog: fromDialog.DialogState;
  layout: fromLayout.LayoutState;
  metadata: fromMetadata.MetadataState;
  sidebar: fromSidebar.SidebarState;
  stomp: fromStomp.StompState;
  themes: fromThemes.ThemesState;
  users: fromUsers.UsersState;
  router: fromRouter.RouterReducerState;
}

export const reducers: ActionReducerMap<AppState> = {
  alert: fromAlert.reducer,
  auth: fromAuth.reducer,
  dialog: fromDialog.reducer,
  layout: fromLayout.reducer,
  metadata: fromMetadata.reducer,
  sidebar: fromSidebar.reducer,
  stomp: fromStomp.reducer,
  themes: fromThemes.reducer,
  users: fromUsers.reducer,
  router: fromRouter.routerReducer
};

export const reducerToken = new InjectionToken<ActionReducerMap<AppState>>('Registered Reducers');

export const reducerProvider = [
  { provide: reducerToken, useValue: reducers }
];

export const metaReducers: MetaReducer<AppState>[] = [
  fromRootStore.universalMetaReducer
];
