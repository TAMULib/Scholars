import { Component, OnInit, Input, ViewEncapsulation, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

import { CollectionView } from '../../core/model/view';
import { TemplateService } from '../../core/service/template.service';

@Component({
    selector: 'scholars-result-view',
    templateUrl: './result-view.component.html',
    styleUrls: ['./result-view.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ResultViewComponent implements OnInit {

    @Input()
    public view: CollectionView;

    @Input()
    public resource: any;

    public resultHtml: string;

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private template: TemplateService
    ) {

    }

    ngOnInit() {
        if (this.resource.uri !== undefined) {
            this.resource.uri = this.resource.uri[0].replace('http://hdl.handle.net/', '');
        }
        this.resultHtml = this.template.renderResult(this.view, this.resource);
        if (isPlatformBrowser(this.platformId)) {
            setTimeout(() => {
                window['_altmetric_embed_init']();
                window['__dimensions_embed'].addBadges();
            });
        }
    }

}
