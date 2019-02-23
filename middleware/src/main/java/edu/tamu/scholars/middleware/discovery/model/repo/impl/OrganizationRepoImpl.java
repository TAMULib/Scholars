package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import edu.tamu.scholars.middleware.discovery.model.Organization;

public class OrganizationRepoImpl extends AbstractSolrDocumentRepoImpl<Organization> {

    @Override
    public String collection() {
        return "organizations";
    }

    @Override
    public Class<Organization> type() {
        return Organization.class;
    }

}
