import { Injectable, Inject, PLATFORM_ID, SystemJsNgModuleLoader } from '@angular/core';
import { isPlatformServer } from '@angular/common';

import { Observable, Observer, of } from 'rxjs';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import { StompSubscription } from '../store/stomp';

import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class StompService {

    private client: any;

    private pending: Map<string, { observer: Observer<StompSubscription>, subscription: StompSubscription }>;

    constructor(@Inject(PLATFORM_ID) private platformId: string) {
        this.pending = new Map<string, { observer: Observer<StompSubscription>, subscription: StompSubscription }>();
    }

    public connect(): Observable<any> {
        if (isPlatformServer(this.platformId)) {
            return of(false);
        }
        const socket = new SockJS(environment.service + '/connect');
        this.client = Stomp.over(socket);

        this.client.onreceipt = (receipt: any): void => {
            if (typeof receipt === 'object') {
                if (receipt.headers.destination) {
                    const channel = receipt.headers.destination;
                    const pending = this.pending.get(channel);
                    pending.observer.next(pending.subscription);
                    pending.observer.complete();
                    this.pending.delete(channel);
                }
            }
        };

        this.client.debug = (frame: any): void => {
            if (environment.stompDebug) {
                console.log(frame);
            }
        };

        const headers = {};
        return new Observable((observer) => {
            this.client.connect(headers, (frame) => {
                observer.next(frame);
                observer.complete();
            }, (error) => {
                if (typeof error === 'object') {
                    if (error.headers.destination) {
                        const channel = error.headers.destination;
                        const pending = this.pending.get(channel);
                        pending.observer.error(error);
                        pending.observer.complete();
                        this.pending.delete(channel);
                    }
                }
            });
        });
    }

    public disconnect(): Observable<any> {
        if (isPlatformServer(this.platformId)) {
            return of(false);
        }
        return new Observable((observer) => {
            if (this.client !== undefined) {
                this.client.disconnect((frame) => {
                    observer.next(frame);
                    observer.complete();
                }, (error) => {
                    observer.error(error);
                    observer.complete();
                });
            } else {
                observer.next('Not connected!');
                observer.complete();
            }
        });
    }

    public subscribe(channel: string, callback: Function): Observable<any> {
        if (isPlatformServer(this.platformId)) {
            return of(false);
        }
        return new Observable((observer) => {
            this.pending.set(channel, {
                observer: observer,
                subscription: this.client.subscribe(channel, callback, {
                    receipt: `receipt-${Object.keys(this.pending).length}`
                })
            });
        });
    }

    public unsubscribe(id: string): Observable<any> {
        return of(this.client.unsubscribe(id));
    }

}
