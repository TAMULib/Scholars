package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Person;

@JsonComponent
public class PersonSerializer extends AbstractSolrDocumentSerializer<Person> {

    private final JsonSerializer<Person> delegate = new UnwrappingPersonSerializer(NameTransformer.NOP);

    @Override
    protected JsonSerializer<Person> getDelegate() {
        return delegate;
    }

    @Override
    public JsonSerializer<Person> unwrappingSerializer(final NameTransformer nameTransformer) {
        return new UnwrappingPersonSerializer(nameTransformer);
    }

}
