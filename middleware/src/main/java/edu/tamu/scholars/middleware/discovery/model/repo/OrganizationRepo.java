package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.discovery.model.Organization;

@Repository
public interface OrganizationRepo extends SolrDocumentRepo<Organization> {

}
