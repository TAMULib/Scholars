package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Concept;

public class UnwrappingConceptSerializer extends AbstractUnwrappingSolrDocumentSerializer<Concept> {

    public UnwrappingConceptSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
