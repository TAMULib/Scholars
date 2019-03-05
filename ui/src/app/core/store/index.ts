import { InjectionToken } from '@angular/core';

import { ActionReducerMap, MetaReducer } from '@ngrx/store';

import {
  Concept,
  Document,
  Organization,
  Person,
  Process,
  Relationship
} from '../model/discovery';

import { Theme } from '../model/theme';
import { User } from '../model/user';

import {
  DirectoryView,
  DiscoveryView,
  ResultView
} from '../model/view';

import * as fromRouter from '@ngrx/router-store';

import * as fromAlert from './alert/alert.reducer';
import * as fromAuth from './auth/auth.reducer';
import * as fromDialog from './dialog/dialog.reducer';
import * as fromLanguage from './language/language.reducer';
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
  language: fromLanguage.LanguageState;
  layout: fromLayout.LayoutState;
  metadata: fromMetadata.MetadataState;
  sidebar: fromSidebar.SidebarState;
  stomp: fromStomp.StompState;
  theme: fromTheme.ThemeState;
  concepts: fromSdr.SdrState<Concept>;
  documents: fromSdr.SdrState<Document>;
  organizations: fromSdr.SdrState<Organization>;
  persons: fromSdr.SdrState<Person>;
  processes: fromSdr.SdrState<Process>;
  relationships: fromSdr.SdrState<Relationship>;
  themes: fromSdr.SdrState<Theme>;
  users: fromSdr.SdrState<User>;
  directoryViews: fromSdr.SdrState<DirectoryView>;
  discoveryViews: fromSdr.SdrState<DiscoveryView>;
  resultViews: fromSdr.SdrState<ResultView>;
  router: fromRouter.RouterReducerState;
}

export const reducers: ActionReducerMap<AppState> = {
  alert: fromAlert.reducer,
  auth: fromAuth.reducer,
  dialog: fromDialog.reducer,
  language: fromLanguage.reducer,
  layout: fromLayout.reducer,
  metadata: fromMetadata.reducer,
  sidebar: fromSidebar.reducer,
  stomp: fromStomp.reducer,
  theme: fromTheme.reducer,
  concepts: fromSdr.getSdrReducer<Concept>('concepts'),
  documents: fromSdr.getSdrReducer<Document>('documents'),
  organizations: fromSdr.getSdrReducer<Organization>('organizations'),
  persons: fromSdr.getSdrReducer<Person>('persons'),
  processes: fromSdr.getSdrReducer<Process>('processes'),
  relationships: fromSdr.getSdrReducer<Relationship>('relationships'),
  themes: fromSdr.getSdrReducer<Theme>('themes'),
  users: fromSdr.getSdrReducer<User>('users'),
  directoryViews: fromSdr.getSdrReducer<DirectoryView>('directoryViews'),
  discoveryViews: fromSdr.getSdrReducer<DiscoveryView>('discoveryViews'),
  resultViews: fromSdr.getSdrReducer<ResultView>('resultViews'),
  router: fromRouter.routerReducer
};

export const reducerToken = new InjectionToken<ActionReducerMap<AppState>>('Registered Reducers');

export const reducerProvider = [
  { provide: reducerToken, useValue: reducers }
];

export const metaReducers: MetaReducer<AppState>[] = [
  fromRootStore.universalMetaReducer
];
