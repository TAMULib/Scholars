import { HttpClientTestingModule } from '@angular/common/http/testing';
import { inject, TestBed } from '@angular/core/testing';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { RestService } from './rest.service';

import { getRequest } from '../../app.browser.module';

describe('RestService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                { provide: REQUEST, useFactory: (getRequest) },
                RestService
            ]
        });
    });

    it('should be created', inject([RestService], (service: RestService) => {
        expect(service).toBeTruthy();
    }));

});
