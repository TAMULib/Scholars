import { inject, TestBed } from '@angular/core/testing';
import { Meta } from '@angular/platform-browser';

import { MetadataService } from './metadata.service';

describe('UserService', () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                Meta,
                MetadataService
            ]
        });
    });

    it('should be created', inject([MetadataService], (service: MetadataService) => {
        expect(service).toBeTruthy();
    }));

});
