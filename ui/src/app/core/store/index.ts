import { InjectionToken } from '@angular/core';

import { ActionReducerMap, MetaReducer } from '@ngrx/store';

import { Theme } from '../model/theme';
import { User } from '../model/user';

import * as fromRouter from '@ngrx/router-store';

import * as fromAlert from './alert/alert.reducer';
import * as fromAuth from './auth/auth.reducer';
import * as fromDialog from './dialog/dialog.reducer';
import * as fromLayout from './layout/layout.reducer';
import * as fromMetadata from './metadata/metadata.reducer';
import * as fromSidebar from './sidebar/sidebar.reducer';
import * as fromSdr from './sdr/sdr.reducer';
import * as fromStomp from './stomp/stomp.reducer';
import * as fromTheme from './theme/theme.reducer';
import * as fromRootStore from './root-store.reducer';

export interface AppState {
  alert: fromAlert.AlertState;
  auth: fromAuth.AuthState;
  dialog: fromDialog.DialogState;
  layout: fromLayout.LayoutState;
  metadata: fromMetadata.MetadataState;
  sidebar: fromSidebar.SidebarState;
  stomp: fromStomp.StompState;
  theme: fromTheme.ThemeState;
  themes: fromSdr.SdrState<Theme>;
  users: fromSdr.SdrState<User>;
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
  theme: fromTheme.reducer,
  themes: fromSdr.getSdrReducer<Theme>('themes'),
  users: fromSdr.getSdrReducer<User>('users'),
  router: fromRouter.routerReducer
};

export const reducerToken = new InjectionToken<ActionReducerMap<AppState>>('Registered Reducers');

export const reducerProvider = [
  { provide: reducerToken, useValue: reducers }
];

export const metaReducers: MetaReducer<AppState>[] = [
  fromRootStore.universalMetaReducer
];
