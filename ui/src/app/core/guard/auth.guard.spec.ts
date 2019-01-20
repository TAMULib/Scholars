import { inject, TestBed } from '@angular/core/testing';
import { TranslateService, TranslateModule } from '@ngx-translate/core';


import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';

import { AuthGuard } from './auth.guard';

import { AlertService } from '../service/alert.service';
import { DialogService } from '../service/dialog.service';

import { metaReducers, reducers } from '../store';

describe('AuthGuard', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                AuthGuard,
                AlertService,
                DialogService,
                TranslateService
            ],
            imports: [
                StoreModule.forRoot(reducers, {
                    metaReducers
                }),
                TranslateModule.forRoot(),
                RouterTestingModule.withRoutes([])
            ]
        });
    });

    it('should be created', inject([AuthGuard], (service: AuthGuard) => {
        expect(service).toBeTruthy();
    }));

});
