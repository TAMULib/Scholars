import { Injectable } from '@angular/core';
import * as Mustache from 'mustache';

import { CollectionView } from '../model/view';

@Injectable({
    providedIn: 'root',
})
export class ResultViewService {

    public compileResultView(view: CollectionView, resource: any): string {
        const key = this.getTemplateKey(view, resource);
        resource.collection = view.collection;
        return Mustache.render(view.templates[key], resource);
    }

    public compileView(template: string, resource: any): string {
        return Mustache.render(template, resource);
    }

    public clearCache(): void {
        Mustache.clearCache();
    }

    private getTemplateKey(view: CollectionView, resource: any): string {
        for (const type of resource.type) {
            if (view.templates[type] !== undefined) {
                return type;
            }
        }
        return 'default';
    }

}
