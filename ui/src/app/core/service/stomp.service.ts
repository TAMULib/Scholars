import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformServer } from '@angular/common';

import { Observable, of } from 'rxjs';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import { StompSubscription } from '../store/stomp';

import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class StompService {

    private client: any;

    constructor(@Inject(PLATFORM_ID) private platformId: string) {

    }

    public connect(): Observable<boolean> {
        if (isPlatformServer(this.platformId)) {
            return of(false);
        }
        const socket = new SockJS(environment.service + '/connect');
        this.client = Stomp.over(socket);
        this.client.debug = this.debug;
        const headers = {};
        return Observable.create((observer) => {
            this.client.connect(headers, () => {
                observer.next(true);
                observer.complete();
            }, (error) => {
                console.error(error);
                observer.next(false);
                observer.complete();
            });
        });
    }

    public disconnect(): Observable<boolean> {
        return Observable.create((observer) => {
            if (this.client !== undefined) {
                this.client.disconnect(() => {
                    observer.next(true);
                    observer.complete();
                });
            } else {
                observer.next(true);
                observer.complete();
            }
        });
    }

    public subscribe(channel: string, callback: Function): Observable<any> {
        return of(this.client.subscribe(channel, callback));
    }

    public unsubscribe(subscription: StompSubscription): Observable<boolean> {
        subscription.unsubscribe();
        return of(true);
    }

    private debug(frame: any): void {
        if (environment.stompDebug) {
            console.log(frame);
        }
    }

}
