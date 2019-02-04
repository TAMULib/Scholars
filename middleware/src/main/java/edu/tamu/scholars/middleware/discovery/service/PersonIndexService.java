package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Person;
import edu.tamu.scholars.middleware.discovery.model.repo.PersonRepo;

@Service
public class PersonIndexService extends AbstractSolrIndexService<Person, PersonRepo> {

    @Override
    public Class<?> type() {
        return Person.class;
    }

}
