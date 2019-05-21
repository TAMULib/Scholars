import { PipeTransform, Pipe } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({ name: 'safeUrl' })
export class SafeUrlPipe implements PipeTransform {

    constructor(private sanitized: DomSanitizer) {

    }

    transform(url) {
        return this.sanitized.bypassSecurityTrustResourceUrl(url);
    }

}
