package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Document;
import edu.tamu.scholars.middleware.discovery.model.repo.DocumentRepo;

@Service
public class DocumentIndexService extends AbstractSolrIndexService<Document, DocumentRepo> {

    @Override
    public Class<?> type() {
        return Document.class;
    }

    @Override
    public void index() {

    }

}
