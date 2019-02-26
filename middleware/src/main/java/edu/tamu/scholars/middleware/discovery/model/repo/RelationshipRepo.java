package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

@RepositoryRestResource
public interface RelationshipRepo extends SolrDocumentRepo<Relationship> {

}
