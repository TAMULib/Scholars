import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SharedModule } from '../shared.module';

import { GridViewComponent } from './grid-view.component';

describe('GridViewComponent', () => {
    let component: GridViewComponent;
    let fixture: ComponentFixture<GridViewComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                SharedModule
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(GridViewComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
