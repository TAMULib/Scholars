package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Person;

public class PersonResource extends AbstractSolrDocumentResource<Person> {

    public PersonResource(Person person, Iterable<Link> links) {
        super(person, links);
    }

}
