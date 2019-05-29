import { Component, Input } from '@angular/core';

@Component({
    selector: 'scholars-youtube',
    templateUrl: './youtube.component.html',
    styleUrls: ['./youtube.component.scss']
})
export class YouTubeComponent {

    @Input()
    public url: string;

}
