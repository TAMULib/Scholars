package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Organization;
import edu.tamu.scholars.middleware.discovery.model.repo.OrganizationRepo;

public class OrganizationControllerTest extends AbstractSolrDocumentControllerTest<Organization, OrganizationRepo> {

    @Value("classpath:mock/discovery/organization")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Organization.class;
    }

    @Override
    protected String getPath() {
        return "/organizations";
    }

}
