import { APP_BASE_HREF } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';

import { scheduled } from 'rxjs';
import { queue } from 'rxjs/internal/scheduler/queue';

import { SharedModule } from '../../shared/shared.module';

import { TabComponent } from './tab.component';

import { metaReducers, reducers } from '../../core/store';

import { routes } from '../display.routes';

describe('TabComponent', () => {
    let component: TabComponent;
    let fixture: ComponentFixture<TabComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                TabComponent
            ],
            imports: [
                SharedModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                RouterTestingModule.withRoutes(routes[0].children)
            ],
            providers: [
                {
                    provide: APP_BASE_HREF,
                    useValue: '/'
                },
                {
                    provide: ActivatedRoute,
                    useValue: {
                        params: scheduled([{ view: 'People', tab: 'View All' }], queue),
                        parent: {
                            params: scheduled([{ collection: 'persons', id: 'test' }], queue),
                        }
                    }
                }
            ],
            schemas: [
                CUSTOM_ELEMENTS_SCHEMA
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(TabComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
