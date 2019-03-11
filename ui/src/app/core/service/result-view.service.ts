import { Injectable } from '@angular/core';
import * as Mustache from 'mustache';

import { CollectionView } from '../model/view';
import { stringify } from '@angular/core/src/render3/util';

@Injectable({
    providedIn: 'root',
})
export class ResultViewService {

    public compileResultView(view: CollectionView, resource: any): string {
        return Mustache.render(view.template, resource);
    }

    public clearCache(): void {
        Mustache.clearCache();
    }

}
