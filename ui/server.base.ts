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

const router = express.Router();

const PORT = Number(process.env.PORT) || 4200;
const HOST = process.env.HOST || 'localhost';
const BASE_HREF = process.env.BASE_HREF || '/';

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
router.get('*.*', express.static(join(DIST_FOLDER, 'browser'), {
    maxAge: '1y'
}));

// All regular routes use the Universal engine
router.get('*', (req, res) => {
    res.render(join(DIST_FOLDER, 'browser', 'index.html'), {
        req,
        res
    });
});

// NOTE: this line must be exact to prebuild with base href, see base.js
server.use('/', router);

// Start up the Node server
server.listen(PORT, HOST, () => {
    console.log(`Node Express server listening on http://${HOST}:${PORT}${BASE_HREF}`);
});
