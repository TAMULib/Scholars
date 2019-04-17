package edu.tamu.scholars.middleware.discovery.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public abstract class AbstractSolrDocumentSerializer<D extends AbstractSolrDocument> extends JsonSerializer<D> {

    protected abstract JsonSerializer<D> getDelegate();

    @Override
    public void serialize(D document, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        getDelegate().serialize(document, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

}
