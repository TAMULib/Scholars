package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import edu.tamu.scholars.middleware.discovery.model.Organization;

@JsonComponent
public class OrganizationSerializer extends AbstractSolrDocumentSerializer<Organization> {

}
