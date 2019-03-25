FROM solr:latest

WORKDIR /opt/solr/server/solr/
ADD . /opt/solr/server/solr/

USER root
RUN chown -R solr /opt/solr/server/solr
USER solr

RUN precreate-core organizations
RUN precreate-core persons
RUN precreate-core documents
RUN precreate-core relationships
RUN precreate-core processes
RUN precreate-core roles
RUN precreate-core concepts
