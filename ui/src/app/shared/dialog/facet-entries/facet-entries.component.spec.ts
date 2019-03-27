import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';
import { StoreModule } from '@ngrx/store';

import { SharedModule } from '../../shared.module';

import { FacetEntriesComponent } from './facet-entries.component';

import { metaReducers, reducers } from '../../../core/store';

describe('FacetEntriesComponent', () => {
    let component: FacetEntriesComponent;
    let fixture: ComponentFixture<FacetEntriesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                NoopAnimationsModule,
                SharedModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                TranslateModule.forRoot()
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FacetEntriesComponent);
        component = fixture.componentInstance;
        component.name = 'Test';
        component.facet = {
            field: undefined,
            entries: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
