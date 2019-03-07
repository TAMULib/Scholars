import { CommonModule } from '@angular/common';
import { Injectable, Compiler, Component, NgModule, ComponentFactory } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { CollectionView } from '../model/view';

@Injectable({
    providedIn: 'root',
})
export class ResultViewService {

    private factories: Map<string, ComponentFactory<any>>;

    constructor(private compiler: Compiler) {
        this.factories = new Map<string, ComponentFactory<any>>();
    }

    public compileDynamicResultView(view: CollectionView): ComponentFactory<any> {
        let factory = this.factories.get(view.name);
        if (factory === undefined) {
            @Component({
                template: view.template,
                styles: view.styles
            })
            class DynamicComponent {
                public resource: any;
                constructor() { }
            }

            @NgModule({
                imports: [
                    BrowserModule,
                    CommonModule
                ],
                declarations: [
                    DynamicComponent
                ]
            })
            class DynamicComponentModule { }

            const dynamicComponentModule = this.compiler.compileModuleAndAllComponentsSync(DynamicComponentModule);

            factory = dynamicComponentModule.componentFactories[0];

            this.factories.set(view.name, factory);
        }
        return factory;
    }

    public removeAllDynamicResultView(): void {
        this.factories.clear();
    }

    public removeDynamicResultView(name: string): void {
        this.factories.delete(name);
    }

}
