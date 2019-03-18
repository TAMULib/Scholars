import { Component, ViewChild, ElementRef, AfterViewInit, HostListener } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

interface ScrollItem {
    src: string;
    link: string;
    alt: string;
    hidden: boolean;
}

@Component({
    selector: 'scholars-recent-publications',
    templateUrl: 'recent-publications.component.html',
    styleUrls: ['recent-publications.component.scss']
})
export class RecentPublicationsComponent implements AfterViewInit {

    @ViewChild('scrollView') scrollViewRef: ElementRef;

    private items: BehaviorSubject<ScrollItem[]>;

    constructor() {
        this.items = new BehaviorSubject<ScrollItem[]>([
            { src: '/assets/images/abcs-of-research.png', link: '#', alt: 'ABCs of Research', hidden: true },
            { src: '/assets/images/advanced-scientific-research.png', link: '#', alt: 'Advanced Scientific Research', hidden: true },
            { src: '/assets/images/educational-research.png', link: '#', alt: 'Educational Research', hidden: true },
            { src: '/assets/images/research-methods.png', link: '#', alt: 'Research Methods', hidden: true },
            { src: '/assets/images/social-research.jpg', link: '#', alt: 'Social Research', hidden: true },
            { src: '/assets/images/thinking-in-research.png', link: '#', alt: 'Thinking in Research', hidden: true }
        ]);
    }

    ngAfterViewInit() {
        setTimeout(() => this.fitItems());
    }

    @HostListener('window:resize', ['$event'])
    public onResize() {
        this.fitItems();
    }

    public getItems(): Observable<ScrollItem[]> {
        return this.items.asObservable();
    }

    public scrollLeft(): void {
        const count = this.getCount();
        const items = this.items.getValue();
        if (items.length > count - 1) {
            items[count - 1].hidden = true;
            items.unshift(items.pop());
            items[0].hidden = false;
            this.items.next(items);
        }
    }

    public scrollRight(): void {
        const count = this.getCount();
        const items = this.items.getValue();
        if (items.length > count - 1) {
            items[0].hidden = true;
            items.push(items.shift());
            items[count - 1].hidden = false;
            this.items.next(items);
        }
    }

    private fitItems(): void {
        const count = this.getCount();
        const items = this.items.getValue();
        for (let i = 0; i < items.length; i++) {
            items[i].hidden = i >= count;
        }
        this.items.next(items);
    }

    private getCount(): number {
        const size = this.scrollViewRef !== undefined ? this.scrollViewRef.nativeElement.clientWidth : 0;
        return Math.floor(size / 80);
    }

}
