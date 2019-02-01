package edu.tamu.scholars.middleware.discovery.service;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public interface SolrIndexService<D extends AbstractSolrDocument> {

    public <S extends D> S index(S document);

    public Class<?> type();

}
