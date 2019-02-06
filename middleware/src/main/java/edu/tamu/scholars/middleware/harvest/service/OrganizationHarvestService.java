package edu.tamu.scholars.middleware.harvest.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Organization;
import edu.tamu.scholars.middleware.discovery.service.OrganizationIndexService;

@Service
public class OrganizationHarvestService extends AbstractHarvestService<Organization, OrganizationIndexService> {

}
