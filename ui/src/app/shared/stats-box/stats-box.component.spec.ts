import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';

import { StatsBoxComponent } from './stats-box.component';

import { metaReducers, reducers } from '../../core/store';

describe('StatsBoxComponent', () => {
    let component: StatsBoxComponent;
    let fixture: ComponentFixture<StatsBoxComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                StatsBoxComponent
            ],
            imports: [
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                RouterTestingModule.withRoutes([])
            ],
            schemas: [
                CUSTOM_ELEMENTS_SCHEMA
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(StatsBoxComponent);
        component = fixture.componentInstance;
        component.label = 'People';
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
