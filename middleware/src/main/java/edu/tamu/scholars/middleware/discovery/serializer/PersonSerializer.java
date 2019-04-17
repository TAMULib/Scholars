package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import edu.tamu.scholars.middleware.discovery.model.Person;

@JsonComponent
public class PersonSerializer extends AbstractSolrDocumentSerializer<Person> {

}
