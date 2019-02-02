package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Publication;
import edu.tamu.scholars.middleware.discovery.model.repo.PublicationRepo;

@Service
public class PublicationIndexService extends AbstractSolrIndexService<Publication, PublicationRepo> {

    @Override
    public Class<?> type() {
        return Publication.class;
    }

}
