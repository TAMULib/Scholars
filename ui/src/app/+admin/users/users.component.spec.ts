import { APP_BASE_HREF } from '@angular/common';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';

import { StoreModule } from '@ngrx/store';

import { SharedModule } from '../../shared/shared.module';

import { DialogService } from '../../core/service/dialog.service';

import { UsersComponent } from './users.component';

import { metaReducers, reducers } from '../../core/store';

describe('UsersComponent', () => {
    let component: UsersComponent;
    let fixture: ComponentFixture<UsersComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                UsersComponent
            ],
            providers: [
                DialogService,
                {
                    provide: APP_BASE_HREF,
                    useValue: '/'
                }
            ],
            imports: [
                NoopAnimationsModule,
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
        fixture = TestBed.createComponent(UsersComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
