package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

public class RelationshipRepoImpl extends AbstractSolrDocumentRepoImpl<Relationship> {

    @Override
    public String collection() {
        return "relationships";
    }

    @Override
    public Class<Relationship> type() {
        return Relationship.class;
    }

}
