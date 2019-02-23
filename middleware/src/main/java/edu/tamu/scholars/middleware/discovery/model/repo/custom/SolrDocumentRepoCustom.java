package edu.tamu.scholars.middleware.discovery.model.repo.custom;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public interface SolrDocumentRepoCustom<D extends AbstractSolrDocument> {

    public FacetPage<D> search(String query, String[] fields, Pageable page);

}
