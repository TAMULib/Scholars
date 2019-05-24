import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';

import { scheduled } from 'rxjs';
import { queue } from 'rxjs/internal/scheduler/queue';

import { DisplayModule } from '../display.module';

import { SubsectionComponent } from './subsection.component';

import { metaReducers, reducers } from '../../core/store';

import { routes } from '../display.routes';

describe('SubsectionComponent', () => {
    let component: SubsectionComponent;
    let fixture: ComponentFixture<SubsectionComponent>;

    const params = {};

    params['View All.size'] = 10;
    params['View All.page'] = 1;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                DisplayModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                RouterTestingModule.withRoutes(routes[0].children)
            ],
            providers: [
                {
                    provide: ActivatedRoute,
                    useValue: {
                        queryParams: scheduled([params], queue)
                    }
                }
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
