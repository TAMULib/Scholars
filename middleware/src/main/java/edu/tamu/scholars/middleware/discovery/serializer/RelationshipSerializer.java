package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

@JsonComponent
public class RelationshipSerializer extends AbstractSolrDocumentSerializer<Relationship> {

    private final JsonSerializer<Relationship> delegate = new UnwrappingRelationshipSerializer(NameTransformer.NOP);

    @Override
    protected JsonSerializer<Relationship> getDelegate() {
        return delegate;
    }

    @Override
    public JsonSerializer<Relationship> unwrappingSerializer(final NameTransformer nameTransformer) {
        return new UnwrappingRelationshipSerializer(nameTransformer);
    }

}
