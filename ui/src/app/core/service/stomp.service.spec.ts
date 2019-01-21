import { inject, TestBed } from '@angular/core/testing';

import { StompService } from './stomp.service';

describe('StompService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                StompService
            ]
        });
    });

    it('should be created', inject([StompService], (service: StompService) => {
        expect(service).toBeTruthy();
    }));

});
