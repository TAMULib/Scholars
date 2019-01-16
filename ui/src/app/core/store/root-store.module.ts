import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { StoreRouterConnectingModule, RouterStateSerializer } from '@ngrx/router-store';

import { CustomRouterStateSerializer } from './router/router.reducer';

import { AlertEffects } from './alert/alert.effects';
import { AuthEffects } from './auth/auth.effects';
import { DialogEffects } from './dialog/dialog.effects';
import { LayoutEffects } from './layout/layout.effects';
import { MetadataEffects } from './metadata/metadata.effects';
import { RootStoreEffects } from './root-store.effects';
import { RouterEffects } from './router/router.effects';
import { SidebarEffects } from './sidebar/sidebar.effects';
import { StompEffects } from './stomp/stomp.effects';
import { ThemesEffects } from './themes/themes.effects';
import { UsersEffects } from './users/users.effects';

import { reducerProvider, metaReducers, reducerToken } from './';

import { environment } from '../../../environments/environment';

@NgModule({
    imports: [
        CommonModule,
        StoreModule.forRoot(reducerToken, {
            metaReducers
        }),
        StoreRouterConnectingModule,
        EffectsModule.forRoot([
            RootStoreEffects,
            RouterEffects,
            UsersEffects,
            ThemesEffects,
            StompEffects,
            SidebarEffects,
            MetadataEffects,
            LayoutEffects,
            DialogEffects,
            AuthEffects,
            AlertEffects
        ]),
        !environment.production && environment.hasStoreDevTools ? StoreDevtoolsModule.instrument({
            maxAge: 25,
        }) : []
    ],
    providers: [
        { provide: RouterStateSerializer, useClass: CustomRouterStateSerializer },
        reducerProvider
    ]
})
export class RootStoreModule { }
