package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Person;

@Repository
public interface PersonRepo extends SolrDocumentRepo<Person> {

}
