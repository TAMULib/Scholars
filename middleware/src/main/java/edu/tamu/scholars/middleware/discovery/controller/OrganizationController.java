package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.discovery.model.Organization;
import edu.tamu.scholars.middleware.discovery.model.repo.OrganizationRepo;

@BasePathAwareController
@RequestMapping("/organizations")
public class OrganizationController extends AbstractSolrDocumentController<Organization, OrganizationRepo> {

}
