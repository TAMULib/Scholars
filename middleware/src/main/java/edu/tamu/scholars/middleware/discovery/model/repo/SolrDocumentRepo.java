package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.solr.repository.SolrCrudRepository;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

@NoRepositoryBean
public interface SolrDocumentRepo<D extends AbstractSolrDocument> extends SolrCrudRepository<D, String> {

    @Override
    @RestResource(exported = false)
    public <S extends D> S save(S document);

    @Override
    @RestResource(exported = false)
    public void delete(D document);

    long count();

}
