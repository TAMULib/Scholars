package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.OrganizationController;
import edu.tamu.scholars.middleware.discovery.model.Organization;
import edu.tamu.scholars.middleware.discovery.resource.OrganizationResource;

@Component
public class OrganizationResourceAssembler extends AbstractSolrDocumentResourceAssembler<Organization, OrganizationResource> {

    public OrganizationResourceAssembler() {
        super(OrganizationController.class, OrganizationResource.class);
    }

    @Override
    protected OrganizationResource createResource(Organization organization, Iterable<Link> links) {
        return new OrganizationResource(organization, links);
    }

}
