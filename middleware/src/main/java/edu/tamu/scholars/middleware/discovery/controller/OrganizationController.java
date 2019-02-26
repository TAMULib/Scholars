package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.discovery.assembler.OrganizationResourceAssembler;
import edu.tamu.scholars.middleware.discovery.model.Organization;
import edu.tamu.scholars.middleware.discovery.model.repo.OrganizationRepo;
import edu.tamu.scholars.middleware.discovery.resource.OrganizationResource;

@BasePathAwareController
@RequestMapping("/organizations")
public class OrganizationController extends AbstractSolrDocumentController<Organization, OrganizationRepo, OrganizationResource, OrganizationResourceAssembler> {

}
