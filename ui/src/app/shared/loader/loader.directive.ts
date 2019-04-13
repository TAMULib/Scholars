import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common';
import { Directive, Input, OnInit, OnDestroy, HostBinding, ElementRef, Renderer2, Inject, PLATFORM_ID } from '@angular/core';

import { Observable, Subscription } from 'rxjs';

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

    @HostBinding('style.background-image') backgroundImage: SafeStyle;

    @HostBinding('style.background-position') backgroundPosition: string;

    @HostBinding('style.background-size') backgroundSize: string;

    @Input() scholarsLoader: Observable<boolean>;

    @Input() loaderType: 'circular' | 'linear';

    @Input() loaderPosition: string;

    @Input() loaderSize: string;

    @Input() loaderColor: string;

    private elementsToBlur: StyledElement[];

    private subscription: Subscription;

    private readonly blurStyles: BlurStyles = {
        color: 'transparent',
        textShadow: '0 0 5px rgba(0,0,0,0.5)',
        opacity: '.5',
        filter: 'blur(5px)',
        pointerEvents: 'none'
    };

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        private sanitizer: DomSanitizer,
        private elementRef: ElementRef,
        private renderer: Renderer2
    ) {

        this.transition = 'all .25s ease-in-out';
        this.backgroundRepeat = 'no-repeat';

        this.loaderType = 'circular';
        this.loaderPosition = 'center center';
        this.loaderSize = '100px 100px';
        this.loaderColor = '#000000';

        this.elementsToBlur = [];
    }

    ngOnInit() {
        if (isPlatformBrowser(this.platformId)) {
            const children = this.elementRef.nativeElement.children;

            if (children.length > 0) {
                for (const childElement of children) {
                    this.elementsToBlur.push(this.makeStyledElement(childElement));
                }
            } else {
                this.elementsToBlur.push(this.makeStyledElement(this.elementRef.nativeElement, true));
            }

            this.subscription = this.scholarsLoader.subscribe((loading: boolean) => {
                if (loading) {

                    setTimeout(() => {
                        this.backgroundImage = this.getBackgroundImage();
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
                    this.backgroundImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/images/transparent.png)');

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
                        this.backgroundPosition = this.loaderSize;
                        this.backgroundSize = this.loaderSize;
                    }, 250);
                }
            });
        }
    }

    ngOnDestroy() {
        if (this.subscription !== undefined) {
            this.subscription.unsubscribe();
        }
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

    private getBackgroundImage(): SafeStyle {
        switch (this.loaderType) {
            case 'linear':
                return this.sanitizer.bypassSecurityTrustStyle(
                    `url(data:image/svg+xml;base64,${btoa(`<svg width='200px' height='200px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='lds-ellipsis' style='background: none;'>
                        <circle cx='84' cy='50' r='0' fill='${this.loaderColor}'>
                            <animate attributeName='r' values='5;0;0;0;0' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='0s'></animate>
                            <animate attributeName='cx' values='84;84;84;84;84' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='0s'></animate>
                        </circle>
                        <circle cx='16' cy='50' r='4.99822' fill='${this.loaderColor}'>
                            <animate attributeName='r' values='0;5;5;5;0' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='-0.75s'></animate>
                            <animate attributeName='cx' values='16;16;50;84;84' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='-0.75s'></animate>
                        </circle>
                        <circle cx='84' cy='50' r='0.00177574' fill='${this.loaderColor}'>
                            <animate attributeName='r' values='0;5;5;5;0' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='-0.375s'></animate>
                            <animate attributeName='cx' values='16;16;50;84;84' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='-0.375s'></animate>
                        </circle>
                        <circle cx='83.9879' cy='50' r='5' fill='${this.loaderColor}'>
                            <animate attributeName='r' values='0;5;5;5;0' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='0s'></animate>
                            <animate attributeName='cx' values='16;16;50;84;84' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='0s'></animate>
                        </circle>
                        <circle cx='49.9879' cy='50' r='5' fill='${this.loaderColor}'>
                            <animate attributeName='r' values='0;0;5;5;5' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='0s'></animate>
                            <animate attributeName='cx' values='16;16;16;50;84' keyTimes='0;0.25;0.5;0.75;1' keySplines='0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1;0 0.5 0.5 1' calcMode='spline' dur='1.5s' repeatCount='indefinite' begin='0s'></animate>
                        </circle>
                    </svg>`)}`
                );
            case 'circular':
                return this.sanitizer.bypassSecurityTrustStyle(
                    `url(data:image/svg+xml;base64,${btoa(`<svg class='lds-spin' width='200px' height='200px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' style='background: none;'>
                        <g transform='translate(80,50)'><g transform='rotate(0)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='1' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-1.35s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-1.35s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(74.27050983124843,67.6335575687742)'><g transform='rotate(36)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.9' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-1.2s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-1.2s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(59.270509831248425,78.53169548885461)'><g transform='rotate(72)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.8' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-1.05s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-1.05s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(40.72949016875158,78.53169548885461)'><g transform='rotate(108)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.7' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-0.9s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-0.9s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(25.72949016875158,67.6335575687742)'><g transform='rotate(144)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.6' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-0.75s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-0.75s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(20,50.00000000000001)'><g transform='rotate(180)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.5' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-0.6s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-0.6s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(25.729490168751575,32.366442431225806)'><g transform='rotate(216)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.4' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-0.45s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-0.45s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(40.729490168751575,21.468304511145394)'><g transform='rotate(252)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.3' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-0.3s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-0.3s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(59.27050983124842,21.46830451114539)'><g transform='rotate(288)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.2' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='-0.15s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='-0.15s'></animate>
                            </circle>
                        </g></g>
                        <g transform='translate(74.27050983124842,32.3664424312258)'><g transform='rotate(324)'>
                            <circle cx='0' cy='0' r='5' fill='${this.loaderColor}' fill-opacity='0.1' transform='scale(1 1)'>
                                <animateTransform attributeName='transform' type='scale' begin='0s' values='1 1;1 1' keyTimes='0;1' dur='1.5s' repeatCount='indefinite'></animateTransform>
                                <animate attributeName='fill-opacity' keyTimes='0;1' dur='1.5s' repeatCount='indefinite' values='1;0' begin='0s'></animate>
                            </circle>
                        </g></g>
                    </svg>`)}`
                );
        }
    }

}
