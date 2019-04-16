package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Organization;
import edu.tamu.scholars.middleware.discovery.model.repo.OrganizationRepo;

@Service
public class OrganizationIndexService extends AbstractSolrIndexService<Organization, OrganizationRepo> {

    @Override
    public Class<?> type() {
        return Organization.class;
    }

    @Override
    public void index() {

    }

}
