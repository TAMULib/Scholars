import { APP_BASE_HREF } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';
import { StoreModule } from '@ngrx/store';

import { SharedModule } from '../shared/shared.module';

import { DirectoryComponent } from './directory.component';

import { routes } from './directory.routes';

import { metaReducers, reducers } from '../core/store';

describe('DirectoryComponent', () => {
    let component: DirectoryComponent;
    let fixture: ComponentFixture<DirectoryComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                DirectoryComponent
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
        fixture = TestBed.createComponent(DirectoryComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
