package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "processes")
@CollectionSource(key = "process.class")
public class Process extends AbstractSolrDocument {

}
