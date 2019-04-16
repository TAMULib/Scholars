package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Relationship;
import edu.tamu.scholars.middleware.discovery.model.repo.RelationshipRepo;

@Service
public class RelationshipIndexService extends AbstractSolrIndexService<Relationship, RelationshipRepo> {

    @Override
    public Class<?> type() {
        return Relationship.class;
    }

    @Override
    public void index() {

    }

}
