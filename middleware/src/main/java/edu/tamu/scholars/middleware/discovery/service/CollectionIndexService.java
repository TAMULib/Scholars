package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Collection;
import edu.tamu.scholars.middleware.discovery.model.repo.CollectionRepo;

@Service
public class CollectionIndexService extends AbstractSolrIndexService<Collection, CollectionRepo> {

    @Override
    public Class<?> type() {
        return Collection.class;
    }

}
