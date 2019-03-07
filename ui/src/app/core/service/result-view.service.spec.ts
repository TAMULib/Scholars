import { inject, TestBed } from '@angular/core/testing';

import { ResultViewService } from './result-view.service';

describe('ResultViewService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                ResultViewService
            ]
        });
    });

    it('should be created', inject([ResultViewService], (service: ResultViewService) => {
        expect(service).toBeTruthy();
    }));

});
