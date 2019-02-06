package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Document;

@Repository
public interface DocumentRepo extends SolrDocumentRepo<Document> {

}
