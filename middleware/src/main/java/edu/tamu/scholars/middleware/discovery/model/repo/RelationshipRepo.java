package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

@Repository
public interface RelationshipRepo extends SolrDocumentRepo<Relationship> {

}
