import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { OrganizationsComponent } from './organizations.component';

describe('OrganizationsComponent', () => {
    let component: OrganizationsComponent;
    let fixture: ComponentFixture<OrganizationsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                OrganizationsComponent
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
        fixture = TestBed.createComponent(OrganizationsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
