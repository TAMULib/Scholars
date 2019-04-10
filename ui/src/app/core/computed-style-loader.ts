import { Injectable } from '@angular/core';

@Injectable()
export abstract class ComputedStyleLoader {
    public abstract getComputedStyle(): CSSStyleDeclaration;
}
