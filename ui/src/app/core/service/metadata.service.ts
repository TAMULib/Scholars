import { Injectable } from '@angular/core';
import { Meta, MetaDefinition } from '@angular/platform-browser';

@Injectable({
    providedIn: 'root',
})
export class MetadataService {

    constructor(private meta: Meta) {

    }

    public addTags(tags: MetaDefinition[]): void {
        tags.forEach((tag: MetaDefinition) => this.addTag(tag));
    }

    public removeTags(tags: MetaDefinition[]): void {
        tags.forEach((tag: MetaDefinition) => this.removeTag(`name="${tag.name}"`));
    }

    public addTag(tag: MetaDefinition): void {
        if (tag.content && tag.content.length > 0) {
            this.meta.addTag(tag);
        }
    }

    public removeTag(selector: string): void {
        this.meta.removeTag(selector);
    }

    public updateTag(tag: MetaDefinition): void {
        if (tag.content && tag.content.length > 0) {
            this.meta.updateTag(tag);
        }
    }

}
