import 'zone.js/dist/zone-node';

import { enableProdMode } from '@angular/core';

// Express Engine
import { ngExpressEngine } from '@nguniversal/express-engine';
// Import module map for lazy loading
import { provideModuleMap } from '@nguniversal/module-map-ngfactory-loader';

import * as compression from 'compression';
import * as express from 'express';

import { join } from 'path';

// Faster server renders w/ Prod mode (dev mode never needed)
enableProdMode();

// Express server
const server = express();
server.use(compression());

const PORT = Number(process.env.PORT) || 4200;
const HOST = process.env.HOST || 'localhost';
const DIST_FOLDER = join(process.cwd(), 'dist');

// * NOTE :: leave this as require() since this file is built Dynamically from webpack
const { AppServerModuleNgFactory, LAZY_MODULE_MAP } = require('./server/main');

// Our Universal express-engine (found @ https://github.com/angular/universal/tree/master/modules/express-engine)
server.engine('html', ngExpressEngine({
    bootstrap: AppServerModuleNgFactory,
    providers: [
        provideModuleMap(LAZY_MODULE_MAP)
    ]
}));

server.set('view engine', 'html');
server.set('views', join(DIST_FOLDER, 'browser'));

// Server static files from /browser
server.get('*.*', express.static(join(DIST_FOLDER, 'browser'), {
    maxAge: '1y'
}));

// All regular routes use the Universal engine
server.get('*', (req, res) => {
    res.render(join(DIST_FOLDER, 'browser', 'index.html'), {
        req,
        res
    });
});

// Start up the Node server
server.listen(PORT, HOST, () => {
    console.log(`Node Express server listening on http://${HOST}:${PORT}`);
});
