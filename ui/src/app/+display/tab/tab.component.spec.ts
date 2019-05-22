import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayModule } from '../display.module';

import { TabComponent } from './tab.component';

describe('TabComponent', () => {
    let component: TabComponent;
    let fixture: ComponentFixture<TabComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                DisplayModule
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(TabComponent);
        component = fixture.componentInstance;
        component.tab = {
            name: 'Test',
            sections: [],
            hidden: false
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
