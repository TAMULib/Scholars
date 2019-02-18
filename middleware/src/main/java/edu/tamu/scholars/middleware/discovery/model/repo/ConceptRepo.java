package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Concept;

@Repository
public interface ConceptRepo extends SolrDocumentRepo<Concept>{

}
