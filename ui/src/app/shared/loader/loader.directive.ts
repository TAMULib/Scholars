import { Directive, Input, OnInit, OnDestroy, HostBinding, ElementRef, Renderer2 } from '@angular/core';

import { Observable, Subscription } from 'rxjs';

export enum LoaderType {
    CIRCULAR = 'circular',
    LINEAR = 'linear'
}

export enum LoaderPosition {
    CENTER = 'center center',
    TOP = 'center top',
    BOTTOM = 'center bottom',
    LEFT = 'left center',
    TOP_LEFT = 'left top',
    BOTTOM_LEFT = 'left bottom',
    RIGHT = 'right center',
    TOP_RIGHT = 'right top',
    BOTTOM_RIGHT = 'right bottom'
}

interface StyledElement {
    element: any;
    filter: string;
    pointerEvents: string;
    color: string;
    textShadow: string;
    root: boolean;
}

@Directive({
    selector: '[scholarsLoader]'
})
export class LoaderDirective implements OnInit, OnDestroy {

    @HostBinding('style.transition') transition: string;

    @HostBinding('style.background-image') backgroundImage: string;

    @HostBinding('style.background-repeat') backgroundRepeat: string;

    @HostBinding('style.background-position') backgroundPosition: string;

    @HostBinding('style.background-size') backgroundSize: string;

    @Input() scholarsLoader: Observable<boolean>;

    @Input() loaderType: LoaderType = LoaderType.CIRCULAR;

    @Input() loaderPosition: LoaderPosition = LoaderPosition.TOP;

    private elementsToBlur: StyledElement[];

    private subscription: Subscription;

    constructor(private elementRef: ElementRef, private renderer: Renderer2) {
        this.transition = 'all .25s ease-in-out';
        this.elementsToBlur = [];
    }

    ngOnInit() {
        const children = this.elementRef.nativeElement.children;

        if (children.length > 0) {
            for (const childElement of children) {
                this.elementsToBlur.push(this.makeStyledElement(childElement));
            }
        } else {
            this.elementsToBlur.push(this.makeStyledElement(this.elementRef.nativeElement, true));
        }

        this.subscription = this.scholarsLoader.subscribe((loading: Boolean) => {
            if (loading) {
                setTimeout(() => {
                    if (this.loaderType === LoaderType.LINEAR) {
                        this.backgroundImage = 'url(/assets/images/linear-loader.svg)';
                    } else if (this.loaderType === LoaderType.CIRCULAR) {
                        this.backgroundImage = 'url(/assets/images/circular-loader.svg)';
                    }
                }, 250);

                this.elementsToBlur.forEach((elementToBlur: StyledElement) => {
                    if (elementToBlur.root) {
                        this.renderer.setStyle(elementToBlur.element, 'color', 'transparent');
                        this.renderer.setStyle(elementToBlur.element, 'text-shadow', '0 0 5px rgba(0,0,0,0.5)');
                    } else {
                        this.renderer.setStyle(elementToBlur.element, 'filter', 'blur(5px)');
                        this.renderer.setStyle(elementToBlur.element, 'pointer-events', 'none');
                    }
                });

                this.backgroundPosition = this.loaderPosition;
            } else {
                this.backgroundImage = 'url(/assets/images/transparent.png)';

                this.elementsToBlur.forEach((elementToBlur: StyledElement) => {
                    if (elementToBlur.root) {
                        this.renderer.setStyle(elementToBlur.element, 'color', elementToBlur.color);
                        this.renderer.setStyle(elementToBlur.element, 'text-shadow', elementToBlur.textShadow);
                    } else {
                        this.renderer.setStyle(elementToBlur.element, 'filter', elementToBlur.filter);
                        this.renderer.setStyle(elementToBlur.element, 'pointer-events', elementToBlur.pointerEvents);
                    }
                });

                setTimeout(() => {
                    this.backgroundRepeat = 'no-repeat';
                    this.backgroundPosition = 'center center';
                    this.backgroundSize = '100px 100px';
                }, 250);
            }
        });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

    private makeStyledElement(element: any, root: boolean = false): StyledElement {
        const styles = getComputedStyle(element);
        return {
            element: element,
            filter: styles['filter'],
            pointerEvents: styles['pointer-events'],
            color: styles['color'],
            textShadow: styles['text-shadow'],
            root: root
        };
    }

}
