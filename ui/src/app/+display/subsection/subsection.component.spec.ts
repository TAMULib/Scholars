import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { StoreModule } from '@ngrx/store';

import { DisplayModule } from '../display.module';

import { SubsectionComponent } from './subsection.component';

import { metaReducers, reducers } from '../../core/store';

describe('SubsectionComponent', () => {
    let component: SubsectionComponent;
    let fixture: ComponentFixture<SubsectionComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                DisplayModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SubsectionComponent);
        component = fixture.componentInstance;
        component.subsection = {
            name: 'Test',
            field: 'publications',
            filters: [],
            sort: [],
            pageSize: 5,
            template: '',
            templateFunction: (resource: any) => ''
        };
        component.document = {
            id: 1,
            type: ['Person']
        };
        component.document['publications'] = [];
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
