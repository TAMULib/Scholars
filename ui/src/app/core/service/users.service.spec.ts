import { HttpClientTestingModule } from '@angular/common/http/testing';
import { inject, TestBed } from '@angular/core/testing';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { RestService } from './rest.service';
import { UsersService } from './users.service';

import { getRequest } from '../../app.browser.module';

describe('UserService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                { provide: REQUEST, useFactory: (getRequest) },
                RestService,
                UsersService
            ]
        });
    });

    it('should be created', inject([UsersService], (service: UsersService) => {
        expect(service).toBeTruthy();
    }));

});
