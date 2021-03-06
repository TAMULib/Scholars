import { Component, OnDestroy, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Params, Router, NavigationStart } from '@angular/router';
import { MetaDefinition } from '@angular/platform-browser';

import { Store, select } from '@ngrx/store';

import { Observable, Subscription, combineLatest, scheduled } from 'rxjs';
import { asap } from 'rxjs/internal/scheduler/asap';
import { filter, tap, map, mergeMap } from 'rxjs/operators';

import { AppState } from '../core/store';

import { DiscoveryView, DisplayView, DisplayTabView, DisplayTabSectionView, LazyReference } from '../core/model/view';

import { WindowDimensions } from '../core/store/layout/layout.reducer';

import { selectWindowDimensions } from '../core/store/layout';
import { SolrDocument } from '../core/model/discovery';
import { Side } from '../core/model/view/display-view';

import { selectResourceById, selectDefaultDiscoveryView, selectDisplayViewByTypes, selectResourceIsLoading, selectAllResources } from '../core/store/sdr';

import * as fromSdr from '../core/store/sdr/sdr.actions';
import * as fromMetadata from '../core/store/metadata/metadata.actions';

const hasRequiredFields = (requiredFields: string[], document: SolrDocument): boolean => {
    for (const requiredField of requiredFields) {
        if (document[requiredField] === undefined) {
            return false;
        }
    }
    return true;
};

export const sectionsToShow = (sections: DisplayTabSectionView[], document: SolrDocument): DisplayTabSectionView[] => {
    return sections.filter((section: DisplayTabSectionView) => !section.hidden && hasRequiredFields(section.requiredFields, document));
};

@Component({
    selector: 'scholars-display',
    templateUrl: 'display.component.html',
    styleUrls: ['display.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class DisplayComponent implements OnDestroy, OnInit {

    public windowDimensions: Observable<WindowDimensions>;

    public displayView: Observable<DisplayView>;

    public discoveryView: Observable<DiscoveryView>;

    public document: Observable<SolrDocument>;

    private subscriptions: Subscription[];

    constructor(
        private store: Store<AppState>,
        private router: Router,
        private route: ActivatedRoute,
        private changeDetRef: ChangeDetectorRef
    ) {
        this.subscriptions = [];
    }

    ngOnDestroy() {
        this.subscriptions.forEach((subscription: Subscription) => {
            subscription.unsubscribe();
        });
    }

    ngOnInit() {
        this.windowDimensions = this.store.pipe(select(selectWindowDimensions));
        this.subscriptions.push(this.router.events.pipe(
            filter(event => event instanceof NavigationStart)
        ).subscribe(() => {
            this.changeDetRef.markForCheck();
        }));
        this.discoveryView = this.store.pipe(
            select(selectDefaultDiscoveryView),
            filter((view: DiscoveryView) => view !== undefined)
        );
        this.subscriptions.push(this.route.params.subscribe((params: Params) => {
            if (params.collection && params.id) {
                this.store.dispatch(new fromSdr.GetOneResourceAction(params.collection, { id: params.id }));
                this.document = this.store.pipe(
                    select(selectResourceById(params.collection, params.id)),
                    filter((document: SolrDocument) => document !== undefined),
                    tap((document: SolrDocument) => {
                        console.log(document);
                        this.displayView = this.store.pipe(
                            select(selectDisplayViewByTypes(document.type)),
                            tap(([displayView, isLoading]) => {
                                if (displayView === undefined && !isLoading) {
                                    this.store.dispatch(new fromSdr.FindByTypesInResourceAction('displayViews', {
                                        types: document.type
                                    }));
                                } else if (displayView && !isLoading) {
                                    if (this.route.children.length === 0) {
                                        let tabName = 'View All';
                                        if (displayView.name !== 'People') {
                                            for (const tab of this.getTabsToShow(displayView.tabs, document)) {
                                                if (!tab.hidden) {
                                                    tabName = tab.name;
                                                    break;
                                                }
                                            }
                                        }
                                        this.router.navigate([displayView.name, tabName], {
                                            relativeTo: this.route,
                                            replaceUrl: true
                                        });
                                    }
                                }
                            }),
                            filter(([displayView]) => displayView !== undefined),
                            mergeMap(([displayView]) => {
                                const lazyObservables: Observable<any>[] = [
                                    scheduled([[{}, false]], asap)
                                ];
                                const isLoaded = {};
                                displayView.tabs.forEach((tab: DisplayTabView) => {
                                    tab.sections.forEach((section: DisplayTabSectionView) => {
                                        section.lazyReferences.forEach((lazyReference: LazyReference) => {
                                            if (document[lazyReference.field] !== undefined) {
                                                if (document[lazyReference.field] instanceof Array) {
                                                    const ids = document[lazyReference.field].map((property) => property.id);
                                                    if (ids.length > 0) {
                                                        this.store.dispatch(new fromSdr.FindByIdInResourceAction(lazyReference.collection, { ids }));
                                                        const lazyObservable = combineLatest([
                                                            this.store.pipe(
                                                                select(selectAllResources(lazyReference.collection)),
                                                                map((values) => {
                                                                    return {
                                                                        field: lazyReference.field,
                                                                        value: values
                                                                    };
                                                                })
                                                            ),
                                                            this.store.pipe(
                                                                select(selectResourceIsLoading(lazyReference.collection)),
                                                                filter((isLoading) => {
                                                                    if (isLoading) {
                                                                        isLoaded[lazyReference.collection] = isLoading;
                                                                    }
                                                                    return !isLoading && isLoaded[lazyReference.collection];
                                                                })
                                                            )
                                                        ]);
                                                        lazyObservables.push(lazyObservable);
                                                    }
                                                } else {
                                                    const id = document[lazyReference.field].id;
                                                    this.store.dispatch(new fromSdr.GetOneResourceAction(lazyReference.collection, { id }));
                                                    const lazyObservable = combineLatest([
                                                        this.store.pipe(
                                                            select(selectResourceById(lazyReference.collection, id)),
                                                            map((value) => {
                                                                return {
                                                                    field: lazyReference.field,
                                                                    value: value
                                                                };
                                                            })
                                                        ),
                                                        this.store.pipe(
                                                            select(selectResourceIsLoading(lazyReference.collection)),
                                                            filter((isLoading) => {
                                                                if (isLoading) {
                                                                    isLoaded[lazyReference.collection] = isLoading;
                                                                }
                                                                return !isLoading && isLoaded[lazyReference.collection];
                                                            })
                                                        )
                                                    ]);
                                                    lazyObservables.push(lazyObservable);
                                                }
                                            }
                                        });
                                    });
                                });
                                return combineLatest([scheduled([displayView], asap), combineLatest(lazyObservables)]);
                            }),
                            tap(([displayView, lazyReferences]) => {
                                lazyReferences.forEach((lazyReference) => {
                                    if (lazyReference[0].field && lazyReference[0].value) {
                                        const property = {};
                                        property[lazyReference[0].field] = lazyReference[0].value;
                                        Object.assign(document, property);
                                    }
                                });
                                this.store.dispatch(new fromMetadata.AddMetadataTagsAction({
                                    tags: this.buildDisplayMetaTags(displayView, document)
                                }));
                                const tabCount = displayView.tabs.length - 1;
                                if (displayView.tabs[tabCount].name === 'View All') {
                                    displayView.tabs.splice(tabCount, 1);
                                }
                                const viewAllTabSections = [];
                                const viewAllTab: DisplayTabView = {
                                    name: 'View All',
                                    hidden: false,
                                    sections: viewAllTabSections
                                };
                                this.getTabsToShow(displayView.tabs, document).forEach((tab: DisplayTabView) => {
                                    this.getSectionsToShow(tab.sections, document).forEach((section: DisplayTabSectionView) => {
                                        viewAllTabSections.push(section);
                                    });
                                });
                                displayView.tabs.push(viewAllTab);
                            }),
                            map(([displayView]) => displayView)
                        );
                    })
                );
            }
        }));
    }

    public getDisplayViewTabRoute(displayView: DisplayView, tab: DisplayTabView): string[] {
        return [displayView.name, tab.name];
    }

    public showMainContent(displayView: DisplayView): boolean {
        return displayView.mainContentTemplate && displayView.mainContentTemplate.length > 0;
    }

    public showLeftScan(displayView: DisplayView): boolean {
        return displayView.leftScanTemplate && displayView.leftScanTemplate.length > 0;
    }

    public showRightScan(displayView: DisplayView): boolean {
        return displayView.rightScanTemplate && displayView.rightScanTemplate.length > 0;
    }

    public showAsideLeft(displayView: DisplayView): boolean {
        return this.showAside(displayView) && displayView.asideLocation === Side.LEFT;
    }

    public showAsideRight(displayView: DisplayView): boolean {
        return this.showAside(displayView) && displayView.asideLocation === Side.RIGHT;
    }

    public showAside(displayView: DisplayView): boolean {
        return displayView.asideTemplate && displayView.asideTemplate.length > 0;
    }

    public getMainContentColSize(displayView: DisplayView): number {
        let colSize = 12;
        if (this.showLeftScan(displayView)) {
            colSize -= 3;
        }
        if (this.showRightScan(displayView)) {
            colSize -= 3;
        }
        return colSize;
    }

    public getLeftScanColSize(displayView: DisplayView): number {
        return 3;
    }

    public getRightScanColSize(displayView: DisplayView): number {
        return 3;
    }

    public getTabsToShow(tabs: DisplayTabView[], document: SolrDocument): DisplayTabView[] {
        return tabs.filter((tab: DisplayTabView) => !tab.hidden && this.getSectionsToShow(tab.sections, document).length > 0);
    }

    public getSectionsToShow(sections: DisplayTabSectionView[], document: SolrDocument): DisplayTabSectionView[] {
        return sectionsToShow(sections, document);
    }

    public isMobile(windowDimensions: WindowDimensions): boolean {
        return windowDimensions.width < 768;
    }

    private buildDisplayMetaTags(displayView: DisplayView, document: SolrDocument): MetaDefinition[] {
        const metaTags: MetaDefinition[] = [];
        for (const name in displayView.metaTemplateFunctions) {
            if (displayView.metaTemplateFunctions.hasOwnProperty(name)) {
                const metaTemplateFunction = displayView.metaTemplateFunctions[name];
                metaTags.push({
                    name: name, content: metaTemplateFunction(document)
                });
            }
        }
        return metaTags;
    }

}
