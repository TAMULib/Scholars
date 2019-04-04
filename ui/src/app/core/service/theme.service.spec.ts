import { DOCUMENT } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { inject, TestBed } from '@angular/core/testing';

import { REQUEST } from '@nguniversal/express-engine/tokens';

import { RestService } from './rest.service';
import { ThemeService } from './theme.service';

import { getRequest, createStyleLoader } from '../../app.browser.module';

import { ComputedStyleLoader } from '../computed-style-loader';

describe('ThemeService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                { provide: REQUEST, useFactory: (getRequest) },
                {
                    provide: ComputedStyleLoader,
                    useFactory: (createStyleLoader),
                    deps: [DOCUMENT]
                },
                RestService,
                ThemeService
            ]
        });
    });

    it('should be created', inject([ThemeService], (service: ThemeService) => {
        expect(service).toBeTruthy();
    }));

});
