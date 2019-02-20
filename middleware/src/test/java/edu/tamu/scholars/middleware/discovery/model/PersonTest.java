package edu.tamu.scholars.middleware.discovery.model;

public class PersonTest extends AbstractSolrDocumentTest<Person> {

    @Override
    protected Class<?> getType() {
        return Person.class;
    }

}
