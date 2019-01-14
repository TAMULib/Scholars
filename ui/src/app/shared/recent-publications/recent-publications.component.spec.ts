import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentPublicationsComponent } from './recent-publications.component';

describe('RecentPublicationsComponent', () => {
    let component: RecentPublicationsComponent;
    let fixture: ComponentFixture<RecentPublicationsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                RecentPublicationsComponent
            ],
            schemas: [
                CUSTOM_ELEMENTS_SCHEMA
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RecentPublicationsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
