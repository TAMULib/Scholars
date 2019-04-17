package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Process;

public class UnwrappingProcessSerializer extends AbstractUnwrappingSolrDocumentSerializer<Process> {

    public UnwrappingProcessSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
