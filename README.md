[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e446450d72d34e4381b81113db6924e0)](https://app.codacy.com/app/wwelling/Scholars?utm_source=github.com&utm_medium=referral&utm_content=TAMULib/Scholars&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/TAMULib/Scholars.svg?branch=master)](https://travis-ci.org/TAMULib/Scholars)
[![Coverage Status](https://coveralls.io/repos/github/TAMULib/Scholars/badge.svg)](https://coveralls.io/github/TAMULib/Scholars)

# TAMU Scholars

Texas A&M University has been using <a href="https://wiki.duraspace.org/display/VIVO/VIVO">VIVO</a> for a few years now and our Scholarly Communications unit has quite a bit of traction here in its use.  While other options existed at the time of this project none of them took advantage of current technology trends.  This combined with the requirements and deadline of internal units at Texas A&M University we decided to create our own front end.  This project was conceived, and started prior to the creation of the <a href="https://wiki.duraspace.org/display/VIVO/VIVO+Scholar+Task+Force">VIVO Scholar Task Force</a>.  But, it does follow the same idea.  

<p align="center">
  <img src="https://webassets.library.tamu.edu/public/files/scholars-ui.png">
</p>

This replacement front end is based on <a href="https://lucene.apache.org/solr/">Solr</a>, <a href="https://spring.io/projects/spring-data-solr">Spring Data</a>, and <a href="https://angular.io/guide/universal">Angular Universal</a>.  The basic goals of this front end are:

<ul>
<li>Align the technology stack as much as possible with the existing VIVO stack to assist with ease of implementation by others if they choose especially smaller libraries.</li>
<li>Read only UI.  NO updating back to the triple store.</li>
<li>100% Search Engine Optimization.  IE:  A person / crawler does not need JavaScript enabled for page rendering.  Server side, and Client side rendering if needed.</li>
</ul>

TAMU Scholars currently harvest directly from VIVO’s triple store and for each high level model in VIVO there is a Solr collection and each of its document properties are populated from parsing response of a SparQL query in which isolates the desired value. We are using Spring Apache Solr with explicit Java models representing a Solr document and utilize the provided implementation of a CRUD repository of the Solr documents.  At runtime each Solr collection can be initiated and re-indexed if needed to dynamically pick up new fields added to VIVO’s triple store.

### Developer Documentation

-   [Contributors Documentation](https://github.com/TAMULib/Scholars/blob/master/CONTRIBUTING.md)
-   [Middleware Documentation](https://github.com/TAMULib/Scholars/blob/master/middleware/README.md)
-   [UI Documentation](https://github.com/TAMULib/Scholars/blob/master/ui/README.md)
-   [API Documentation](https://tamulib.github.io/Scholars)

Please feel free to file any issues concerning TAMU Scholars to the issues section of the repository. Any questions concerning TAMU Scholars can be directed to [helpdesk@library.tamu.edu](<>)
