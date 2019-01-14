import { ApplicationRef, ComponentRef, NgModuleRef } from '@angular/core';
import { createNewHosts } from '@angularclass/hmr';
import { flow } from 'lodash/fp';

export const hmrBootstrap = (module: any, bootstrap: () => Promise<NgModuleRef<any>>) => {
    module.hot.accept();
    const bootstrap$ = bootstrap();
    module.hot.dispose(async () => {
        const ngModule = await bootstrap$;
        const elements = flow((cr: NgModuleRef<any>) => cr.injector.get(ApplicationRef), cr => cr.components.map((component: ComponentRef<any>) => component.location.nativeElement))(ngModule);
        const makeVisible = createNewHosts(elements);
        ngModule.destroy();
        makeVisible();
    });
};
