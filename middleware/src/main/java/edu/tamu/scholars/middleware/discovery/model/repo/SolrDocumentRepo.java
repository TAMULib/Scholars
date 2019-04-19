package edu.tamu.scholars.middleware.discovery.model.repo;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.solr.repository.SolrCrudRepository;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.custom.SolrDocumentRepoCustom;

@NoRepositoryBean
public interface SolrDocumentRepo<D extends AbstractSolrDocument> extends SolrCrudRepository<D, String>, SolrDocumentRepoCustom<D> {

    @Override
    @RestResource(exported = false)
    public <S extends D> S save(S document);

    @Override
    @RestResource(exported = false)
    public void delete(D document);

    // NOTE: must export at least one named search method in order for custom search links to show in HAL browser
    public List<D> findByType(String type);

    public List<D> findByIdIn(List<String> ids);

}
