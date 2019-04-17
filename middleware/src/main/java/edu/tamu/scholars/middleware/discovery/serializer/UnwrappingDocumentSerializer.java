package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Document;

public class UnwrappingDocumentSerializer extends AbstractUnwrappingSolrDocumentSerializer<Document> {

    public UnwrappingDocumentSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
