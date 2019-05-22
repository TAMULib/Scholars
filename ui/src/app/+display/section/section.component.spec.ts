import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayModule } from '../display.module';

import { SectionComponent } from './section.component';

describe('SectionComponent', () => {
    let component: SectionComponent;
    let fixture: ComponentFixture<SectionComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                DisplayModule
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SectionComponent);
        component = fixture.componentInstance;
        component.section = {
            name: 'Test',
            template: '',
            templateFunction: (resource: any) => '',
            requiredFields: [],
            lazyReferences: [],
            subsections: [],
            hidden: false
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
