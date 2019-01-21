import { inject, TestBed } from '@angular/core/testing';
import { TranslateService, TranslateModule } from '@ngx-translate/core';

import { DialogService } from './dialog.service';

describe('DialogService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                DialogService,
                TranslateService
            ],
            imports: [
                TranslateModule.forRoot()
            ]
        });
    });

    it('should be created', inject([DialogService], (service: DialogService) => {
        expect(service).toBeTruthy();
    }));

});
