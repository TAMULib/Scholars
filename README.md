[![Build Status](https://travis-ci.org/TAMULib/Scholars.svg?branch=master)](https://travis-ci.org/TAMULib/Scholars)
[![Coverage Status](https://coveralls.io/repos/github/TAMULib/Scholars/badge.svg)](https://coveralls.io/github/TAMULib/Scholars)

# TAMULib Scholars

Texas A&M University has been using VIVO for a few years now and our Scholarly Communications unit has quite a bit of traction here in its use.  While other options existed at the time of this project none of them took advantage of current technology trends.  This combined with the requirements and deadline of internal units at Texas A&M University we decided to create our own front end.  This project was conceived, and started prior to the creation of the <a href="https://wiki.duraspace.org/display/VIVO/VIVO+Scholar+Task+Force" target="_blank">VIVO Scholar Task Force</a>.  But, it does follow the same idea.  

<img src="https://webassets.library.tamu.edu/public/files/scholars-ui.png">

This replacement front end is based on Solr, Spring Data, and Angular Universal.  The basic goals of this front end are:
<ul>
<li>Align the technology stack as much as possible with the existing VIVO stack to assist with ease of implementation by others if they choose especially smaller libraries.</li>
<li>Read only UI.  NO updating back to the triple store.</li>
<li>100% Search Engine Optimization.  IE:  A person / crawler does not need JavaScript enabled for page rendering.  Server side, and Client side rendering if needed.</li>
</ul>

TAMULib Scholars currently harvest directly from VIVO’s triple store and for each high level model in VIVO there is a Solr collection and each of its document properties are populated from parsing response of a SparQL query in which isolates the desired value. We are using Spring Apache Solr with explicit Java models representing a Solr document and utilize the provided implementation of a CRUD repository of the Solr documents.  At runtime each Solr collection can be initiated and re-indexed if needed to dynamically pick up new fields added to VIVO’s triple store.
