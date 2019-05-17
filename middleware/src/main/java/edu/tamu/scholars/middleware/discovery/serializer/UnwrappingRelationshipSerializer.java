package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

public class UnwrappingRelationshipSerializer extends AbstractUnwrappingSolrDocumentSerializer<Relationship> {

    public UnwrappingRelationshipSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
