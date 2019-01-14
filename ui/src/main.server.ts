// https://github.com/angular/angular/issues/15730
import * as xhr2 from 'xhr2';

xhr2.prototype._restrictedHeaders.cookie = false;

export { AppServerModule } from './app/app.server.module';
