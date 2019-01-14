import { Component } from '@angular/core';

@Component({
    selector: 'scholars-copyright',
    templateUrl: 'copyright.component.html',
    styleUrls: ['copyright.component.scss']
})
export class CopyrightComponent {

    public updated: Date = new Date();

}
