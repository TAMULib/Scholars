import { isPlatformBrowser } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { PLATFORM_ID, Inject, NgModule } from '@angular/core';
import { BrowserModule, makeStateKey, TransferState } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TransferHttpCacheModule } from '@nguniversal/common';
import { Store } from '@ngrx/store';

import { AppComponent } from './app.component';

import { routes } from './app.routes';

import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { HeaderModule } from './header/header.module';
import { FooterModule } from './footer/footer.module';
import { RootStoreModule } from './core/store/root-store.module';

import { AppState } from './core/store';

import * as fromStore from './core/store/root-store.actions';

export const NGRX_STATE = makeStateKey('NGRX_STATE');

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule.withServerTransition({ appId: 'scholars-discovery' }),
        TransferHttpCacheModule,
        BrowserAnimationsModule,
        HttpClientModule,
        RouterModule.forRoot(routes, { initialNavigation: 'enabled' }),
        CoreModule.forRoot(),
        NgbModule,
        SharedModule,
        HeaderModule,
        FooterModule,
        RootStoreModule
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {

    public constructor(
        @Inject(PLATFORM_ID) platformId: string,
        private readonly transferState: TransferState,
        private readonly store: Store<AppState>
    ) {
        if (isPlatformBrowser(platformId)) {
            this.onBrowser();
        } else {
            this.onServer();
        }
    }

    onServer() {
        this.transferState.onSerialize(NGRX_STATE, () => {
            this.store.subscribe((state: any) => {
                return state;
            }).unsubscribe();
        });
    }

    onBrowser() {
        const state = this.transferState.get<any>(NGRX_STATE, {});
        this.transferState.remove(NGRX_STATE);
        this.store.dispatch(new fromStore.RehydrateAction(state));
    }

}
