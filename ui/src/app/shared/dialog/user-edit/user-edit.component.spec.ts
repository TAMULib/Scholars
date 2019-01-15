import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { StoreModule } from '@ngrx/store';

import { SharedModule } from '../../shared.module';

import { UserEditComponent } from './user-edit.component';

import { Role } from '../../../core/model/user';

import { metaReducers, reducers } from '../../../core/store';

describe('UserEditComponent', () => {
    let component: UserEditComponent;
    let fixture: ComponentFixture<UserEditComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                NoopAnimationsModule,
                SharedModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                })
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(UserEditComponent);
        component = fixture.componentInstance;
        component.user = {
            firstName: '',
            lastName: '',
            email: '',
            role: Role.ROLE_USER,
            active: false,
            enabled: false,
            _links: undefined
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
