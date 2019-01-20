import { inject, TestBed } from '@angular/core/testing';
import { TranslateService, TranslateModule } from '@ngx-translate/core';

import { AlertService } from './alert.service';

describe('AlertService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                AlertService,
                TranslateService
            ],
            imports: [
                TranslateModule.forRoot()
            ]
        });
    });

    it('should be created', inject([AlertService], (service: AlertService) => {
        expect(service).toBeTruthy();
    }));

});
