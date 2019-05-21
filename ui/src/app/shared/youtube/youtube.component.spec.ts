import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SharedModule } from '../shared.module';

import { YouTubeComponent } from './youtube.component';

describe('YouTubeComponent', () => {
    let component: YouTubeComponent;
    let fixture: ComponentFixture<YouTubeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                SharedModule
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(YouTubeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
