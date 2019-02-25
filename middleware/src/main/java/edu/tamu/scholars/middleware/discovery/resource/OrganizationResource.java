package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Organization;

public class OrganizationResource extends AbstractSolrDocumentResource<Organization> {

    public OrganizationResource(Organization organization, Iterable<Link> links) {
        super(organization, links);
    }

}
