import { Component, Input } from '@angular/core';

@Component({
    selector: 'scholars-directory',
    templateUrl: 'directory.component.html',
    styleUrls: ['directory.component.scss']
})
export class DirectoryComponent {

    @Input() listing: string;

}
