package edu.tamu.scholars.middleware.discovery.model.repo.custom;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.util.MultiValueMap;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public interface SolrDocumentRepoCustom<D extends AbstractSolrDocument> {

    public FacetPage<D> search(String query, String index, String[] facets, MultiValueMap<String, String> params, Pageable page);

}
