import { Injectable } from '@angular/core';
import { Meta, MetaDefinition } from '@angular/platform-browser';

@Injectable({
    providedIn: 'root',
})
export class MetadataService {

    constructor(private meta: Meta) {

    }

    public addTags(tags: MetaDefinition[]): void {
        this.meta.addTags(tags);
    }

    public addTag(tag: MetaDefinition): void {
        this.meta.addTag(tag);
    }

    public removeTag(selector: string): void {
        this.meta.removeTag(selector);
    }

    public updateTag(tag: MetaDefinition): void {
        this.meta.updateTag(tag);
    }

}
