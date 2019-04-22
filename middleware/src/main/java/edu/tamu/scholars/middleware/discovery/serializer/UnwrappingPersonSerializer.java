package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Person;

public class UnwrappingPersonSerializer extends AbstractUnwrappingSolrDocumentSerializer<Person> {

    public UnwrappingPersonSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
