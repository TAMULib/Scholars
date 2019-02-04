package edu.tamu.scholars.middleware.harvest.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Publication;
import edu.tamu.scholars.middleware.discovery.service.PublicationIndexService;

@Service
public class PublicationHarvestService extends AbstractHarvestService<Publication, PublicationIndexService> {

}
