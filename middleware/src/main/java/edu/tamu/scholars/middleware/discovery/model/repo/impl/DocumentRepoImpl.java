package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import edu.tamu.scholars.middleware.discovery.model.Document;

public class DocumentRepoImpl extends AbstractSolrDocumentRepoImpl<Document> {

    @Override
    public String collection() {
        return "documents";
    }

    @Override
    public Class<Document> type() {
        return Document.class;
    }

}
