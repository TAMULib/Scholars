import { APP_BASE_HREF } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';
import { StoreModule } from '@ngrx/store';

import { SharedModule } from '../shared/shared.module';

import { DiscoveryComponent } from './discovery.component';

import { routes } from './discovery.routes';

import { metaReducers, reducers } from '../core/store';

describe('DiscoveryComponent', () => {
    let component: DiscoveryComponent;
    let fixture: ComponentFixture<DiscoveryComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                DiscoveryComponent
            ],
            imports: [
                SharedModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                TranslateModule.forRoot(),
                RouterTestingModule.withRoutes(routes)
            ],
            providers: [
                {
                    provide: APP_BASE_HREF,
                    useValue: '/'
                }
            ],
            schemas: [
                CUSTOM_ELEMENTS_SCHEMA
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(DiscoveryComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
