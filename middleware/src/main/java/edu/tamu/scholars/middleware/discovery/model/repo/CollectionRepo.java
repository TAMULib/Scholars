package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.tamu.scholars.middleware.discovery.model.Collection;

@RepositoryRestResource
public interface CollectionRepo extends SolrDocumentRepo<Collection> {

}
