package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.model.Organization;

@JsonComponent
public class OrganizationSerializer extends AbstractSolrDocumentSerializer<Organization> {

    private final JsonSerializer<Organization> delegate = new UnwrappingOrganizationSerializer(NameTransformer.NOP);

    @Override
    protected JsonSerializer<Organization> getDelegate() {
        return delegate;
    }

    @Override
    public JsonSerializer<Organization> unwrappingSerializer(final NameTransformer nameTransformer) {
        return new UnwrappingOrganizationSerializer(nameTransformer);
    }

}
