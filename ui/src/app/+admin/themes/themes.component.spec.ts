import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';

import { StoreModule } from '@ngrx/store';

import { CoreModule } from '../../core/core.module';
import { SharedModule } from '../../shared/shared.module';

import { ThemesComponent } from './themes.component';

import { metaReducers, reducers } from '../../core/store';

describe('ThemesComponent', () => {
    let component: ThemesComponent;
    let fixture: ComponentFixture<ThemesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                ThemesComponent
            ],
            imports: [
                NoopAnimationsModule,
                CoreModule,
                SharedModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                TranslateModule.forRoot(),
                RouterTestingModule.withRoutes([])
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ThemesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
