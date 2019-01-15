import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class StompService {

    private client: any;

    public connect(): Observable<boolean> {
        const socket = new SockJS(environment.service + '/connect');
        this.client = Stomp.over(socket);
        this.client.debug = (frame) => {
            // console.info(frame);
        };
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
        return Observable.create((observer) => {
            observer.next(this.client.subscribe(channel, callback));
            observer.complete();
        });
    }

    public unsubscribe(subscription: any): Observable<boolean> {
        return Observable.create((observer) => {
            subscription.unsubscribe();
            observer.next(true);
            observer.complete();
        });
    }

}
