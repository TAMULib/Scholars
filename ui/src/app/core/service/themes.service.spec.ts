import { HttpClientTestingModule } from '@angular/common/http/testing';
import { inject, TestBed } from '@angular/core/testing';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { RestService } from './rest.service';
import { ThemesService } from './themes.service';

import { getRequest } from '../../app.browser.module';

describe('ThemeService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                { provide: REQUEST, useFactory: (getRequest) },
                RestService,
                ThemesService
            ]
        });
    });

    it('should be created', inject([ThemesService], (service: ThemesService) => {
        expect(service).toBeTruthy();
    }));

});
