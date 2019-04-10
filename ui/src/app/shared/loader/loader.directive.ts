import { Directive, Input, OnInit, OnDestroy, HostBinding, ElementRef, Renderer2 } from '@angular/core';

import { Observable, Subscription } from 'rxjs';

export enum LoaderType {
    CIRCULAR = 'circular',
    LINEAR = 'linear'
}

interface BlurStyles {
    color: string;
    textShadow: string;
    opacity: string;
    filter: string;
    pointerEvents: string;
}

interface StyledElement extends BlurStyles {
    element: any;
    root: boolean;
}

@Directive({
    selector: '[scholarsLoader]'
})
export class LoaderDirective implements OnInit, OnDestroy {

    @HostBinding('style.transition') readonly transition: string;

    @HostBinding('style.background-repeat') readonly backgroundRepeat: string;

    @HostBinding('style.background-image') backgroundImage: string;

    @HostBinding('style.background-position') backgroundPosition: string;

    @HostBinding('style.background-size') backgroundSize: string;

    @Input() scholarsLoader: Observable<boolean>;

    @Input() loaderType: LoaderType;

    @Input() loaderPosition: string;

    @Input() loaderSize: string;

    private elementsToBlur: StyledElement[];

    private subscription: Subscription;

    private readonly blurStyles: BlurStyles = {
        color: 'transparent',
        textShadow: '0 0 5px rgba(0,0,0,0.5)',
        opacity: '.5',
        filter: 'blur(5px)',
        pointerEvents: 'none'
    };

    constructor(private elementRef: ElementRef, private renderer: Renderer2) {
        this.transition = 'all .25s ease-in-out';
        this.backgroundRepeat = 'no-repeat';

        this.loaderType = LoaderType.CIRCULAR;
        this.loaderPosition = 'center center';
        this.loaderSize = '100px 100px';

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
                        this.backgroundImage = 'url(assets/images/linear-loader.svg)';
                    } else if (this.loaderType === LoaderType.CIRCULAR) {
                        this.backgroundImage = 'url(assets/images/circular-loader.svg)';
                    }
                }, 250);

                this.elementsToBlur.forEach((elementToBlur: StyledElement) => {
                    if (elementToBlur.root) {
                        this.renderer.setStyle(elementToBlur.element, 'color', this.blurStyles.color);
                        this.renderer.setStyle(elementToBlur.element, 'text-shadow', this.blurStyles.textShadow);
                    } else {
                        this.renderer.setStyle(elementToBlur.element, 'opacity', this.blurStyles.opacity);
                        this.renderer.setStyle(elementToBlur.element, 'filter', this.blurStyles.filter);
                        this.renderer.setStyle(elementToBlur.element, 'pointer-events', this.blurStyles.pointerEvents);
                    }
                });

                this.backgroundSize = this.loaderSize;
                this.backgroundPosition = this.loaderPosition;
            } else {
                this.backgroundImage = 'url(assets/images/transparent.png)';

                this.elementsToBlur.forEach((elementToBlur: StyledElement) => {
                    if (elementToBlur.root) {
                        this.renderer.setStyle(elementToBlur.element, 'color', elementToBlur.color);
                        this.renderer.setStyle(elementToBlur.element, 'text-shadow', elementToBlur.textShadow);
                    } else {
                        this.renderer.setStyle(elementToBlur.element, 'opacity', elementToBlur.opacity);
                        this.renderer.setStyle(elementToBlur.element, 'filter', elementToBlur.filter);
                        this.renderer.setStyle(elementToBlur.element, 'pointer-events', elementToBlur.pointerEvents);
                    }
                });

                setTimeout(() => {
                    this.backgroundSize = '100px 100px';
                    this.backgroundPosition = 'center center';
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
            color: styles['color'],
            textShadow: styles['text-shadow'],
            root: root,
            opacity: styles['opacity'],
            filter: styles['filter'],
            pointerEvents: styles['pointer-events']
        };
    }

}
