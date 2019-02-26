package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.PersonController;
import edu.tamu.scholars.middleware.discovery.model.Person;
import edu.tamu.scholars.middleware.discovery.resource.PersonResource;

@Component
public class PersonResourceAssembler extends AbstractSolrDocumentResourceAssembler<Person, PersonResource> {

    public PersonResourceAssembler() {
        super(PersonController.class, PersonResource.class);
    }

    @Override
    protected PersonResource createResource(Person person, Iterable<Link> links) {
        return new PersonResource(person, links);
    }

}
