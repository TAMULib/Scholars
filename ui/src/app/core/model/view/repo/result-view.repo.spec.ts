import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed, inject } from '@angular/core/testing';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { StoreModule } from '@ngrx/store';

import { RestService } from '../../../service/rest.service';
import { ResultViewRepo } from './result-view.repo';

import { metaReducers, reducers } from '../../../store';

import { getRequest } from '../../../../app.browser.module';

describe('ResultViewRepo', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                StoreModule.forRoot(reducers, {
                    metaReducers
                })
            ],
            providers: [
                { provide: REQUEST, useFactory: (getRequest) },
                RestService,
                ResultViewRepo
            ]
        });
    });

    it('should be created', inject([ResultViewRepo], (service: ResultViewRepo) => {
        expect(service).toBeTruthy();
    }));

});
