import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { CopyrightComponent } from './copyright.component';

describe('CopyrightComponent', () => {
    let component: CopyrightComponent;
    let fixture: ComponentFixture<CopyrightComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                CopyrightComponent
            ],
            imports: [
                TranslateModule.forRoot()
            ],
            schemas: [
                CUSTOM_ELEMENTS_SCHEMA
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CopyrightComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
