package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import edu.tamu.scholars.middleware.discovery.model.Concept;

public class ConceptRepoImpl extends AbstractSolrDocumentRepoImpl<Concept> {

    @Override
    public String collection() {
        return "concepts";
    }

    @Override
    public Class<Concept> type() {
        return Concept.class;
    }

}
