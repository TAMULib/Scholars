import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';

import { StoreModule } from '@ngrx/store';

import { SharedModule } from '../../shared/shared.module';

import { DialogService } from '../../core/service/dialog.service';

import { DiscoveryViewsComponent } from './discovery-views.component';

import { metaReducers, reducers } from '../../core/store';

describe('DiscoveryViewsComponent', () => {
    let component: DiscoveryViewsComponent;
    let fixture: ComponentFixture<DiscoveryViewsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                DiscoveryViewsComponent
            ],
            providers: [
                DialogService
            ],
            imports: [
                NoopAnimationsModule,
                SharedModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                TranslateModule.forRoot(),
                RouterTestingModule.withRoutes([])
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(DiscoveryViewsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
