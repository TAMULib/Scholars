import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed, inject } from '@angular/core/testing';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { StoreModule } from '@ngrx/store';

import { RestService } from '../../../service/rest.service';
import { DiscoveryViewRepo } from './discovery-view.repo';

import { metaReducers, reducers } from '../../../store';

import { getRequest } from '../../../../app.browser.module';

describe('DiscoveryViewRepo', () => {

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
                DiscoveryViewRepo
            ]
        });
    });

    it('should be created', inject([DiscoveryViewRepo], (service: DiscoveryViewRepo) => {
        expect(service).toBeTruthy();
    }));

});
