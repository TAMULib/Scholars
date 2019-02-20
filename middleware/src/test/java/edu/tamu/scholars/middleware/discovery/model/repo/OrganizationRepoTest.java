package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Organization;

public class OrganizationRepoTest extends AbstractSolrDocumentRepoTest<Organization, OrganizationRepo> {

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

}
