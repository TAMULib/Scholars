package edu.tamu.scholars.middleware.discovery.serializer;

import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Organization;

public class UnwrappingOrganizationSerializer extends AbstractUnwrappingSolrDocumentSerializer<Organization> {

    public UnwrappingOrganizationSerializer(NameTransformer nameTransformer) {
        super(nameTransformer);
    }

}
