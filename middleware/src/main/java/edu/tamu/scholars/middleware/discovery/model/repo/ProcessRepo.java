package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.tamu.scholars.middleware.discovery.model.Process;

@RepositoryRestResource
public interface ProcessRepo extends SolrDocumentRepo<Process> {

}
