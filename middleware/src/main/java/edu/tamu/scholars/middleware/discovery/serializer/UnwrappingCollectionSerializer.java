package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Collection;

public class UnwrappingCollectionSerializer extends AbstractUnwrappingSolrDocumentSerializer<Collection> {

    public UnwrappingCollectionSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
