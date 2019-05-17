import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';
import { StoreModule } from '@ngrx/store';

import { scheduled } from 'rxjs';
import { asap } from 'rxjs/internal/scheduler/asap';

import { SharedModule } from '../shared.module';

import { DialogComponent } from './dialog.component';
import { DialogButtonType } from '../../core/model/dialog';

import { metaReducers, reducers } from '../../core/store';

describe('DialogComponent', () => {
    let component: DialogComponent;
    let fixture: ComponentFixture<DialogComponent>;

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
        fixture = TestBed.createComponent(DialogComponent);
        component = fixture.componentInstance;
        component.dialog = {
            title: scheduled(['Login'], asap),
            form: undefined,
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: scheduled(['Cancel'], asap),
                action: () => { },
                disabled: () => scheduled([false], asap)
            }
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
