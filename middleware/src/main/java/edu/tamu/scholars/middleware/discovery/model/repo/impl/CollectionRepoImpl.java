package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import edu.tamu.scholars.middleware.discovery.model.Collection;

public class CollectionRepoImpl extends AbstractSolrDocumentRepoImpl<Collection> {

    @Override
    public String collection() {
        return "collections";
    }

    @Override
    public Class<Collection> type() {
        return Collection.class;
    }

}
