package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Person;
import edu.tamu.scholars.middleware.discovery.model.repo.PersonRepo;

public class PersonControllerTest extends AbstractSolrDocumentControllerTest<Person, PersonRepo> {

    @Value("classpath:mock/discovery/person")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Person.class;
    }

    @Override
    protected String getPath() {
        return "/persons";
    }

}
