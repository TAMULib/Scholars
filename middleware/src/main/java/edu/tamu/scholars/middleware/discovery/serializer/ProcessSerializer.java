package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Process;

@JsonComponent
public class ProcessSerializer extends AbstractSolrDocumentSerializer<Process> {

    private final JsonSerializer<Process> delegate = new UnwrappingProcessSerializer(NameTransformer.NOP);

    @Override
    protected JsonSerializer<Process> getDelegate() {
        return delegate;
    }

    @Override
    public JsonSerializer<Process> unwrappingSerializer(final NameTransformer nameTransformer) {
        return new UnwrappingProcessSerializer(nameTransformer);
    }

}
