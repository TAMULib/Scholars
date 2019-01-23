package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Person;

@Repository
public interface PersonRepo extends SolrCrudRepository<Person, String> {

}
