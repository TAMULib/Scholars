package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.repository.SolrCrudRepository;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public abstract class AbstractSolrIndexService<D extends AbstractSolrDocument, R extends SolrCrudRepository<D, String>> implements SolrIndexService<D> {

    @Autowired
    private R repo;

    public <S extends D> S index(S document) {
        return repo.save(document);
    }

}
