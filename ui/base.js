#! /usr/bin/env node

const fs = require("fs");

const BASE_HREF = process.env.BASE_HREF || '/';

if (!BASE_HREF.startsWith('/')) {
    BASE_HREF = `/${BASE_HREF}`;
}

fs.readFile("angular.base.json", function (err, buf) {
    let angularFile = buf.toString();
    const baseHref = BASE_HREF === '/' ? BASE_HREF : `${BASE_HREF}/`;
    const deployUrl = BASE_HREF === '/' ? BASE_HREF : `${BASE_HREF}/`;
    angularFile = angularFile.replace(`"baseHref": "/"`, `"baseHref": "${baseHref}"`);
    angularFile = angularFile.replace(`"deployUrl": "/"`, `"deployUrl": "${deployUrl}"`);
    fs.writeFile("angular.json", angularFile, function (err, data) {
        if (err) console.log(err);
    });
});

fs.readFile("server.base.ts", function (err, buf) {
    let serverFile = buf.toString();
    serverFile = serverFile.replace(`server.use('/', router);`, `server.use('${BASE_HREF}', router);`);
    fs.writeFile("server.ts", serverFile, function (err, data) {
        if (err) console.log(err);
    });
});
