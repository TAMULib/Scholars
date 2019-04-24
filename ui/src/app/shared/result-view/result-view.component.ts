import { Component, OnInit, Input, ViewEncapsulation, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

import { CollectionView } from '../../core/model/view';

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

    constructor(@Inject(PLATFORM_ID) private platformId: string) {

    }

    ngOnInit() {
        this.resultHtml = this.getResultHtml();
        if (isPlatformBrowser(this.platformId)) {
            setTimeout(() => {
                window['_altmetric_embed_init']();
                window['__dimensions_embed'].addBadges();
            });
        }
    }

    private getResultHtml(): string {
        const templateFunction = this.getTemplateFunction();
        return templateFunction(this.resource);
    }

    private getTemplateFunction(): (document: any) => string {
        for (const type of this.resource.type) {
            if (this.view.templateFunctions[type] !== undefined) {
                return this.view.templateFunctions[type];
            }
        }
        return this.view.templateFunctions['default'];
    }

}
