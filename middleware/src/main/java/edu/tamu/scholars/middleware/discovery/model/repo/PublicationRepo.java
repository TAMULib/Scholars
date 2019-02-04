package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Publication;

@Repository
public interface PublicationRepo extends SolrDocumentRepo<Publication> {

}
