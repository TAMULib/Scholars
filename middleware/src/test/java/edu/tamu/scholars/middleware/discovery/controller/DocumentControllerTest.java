package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Document;
import edu.tamu.scholars.middleware.discovery.model.repo.DocumentRepo;

public class DocumentControllerTest extends AbstractSolrDocumentControllerTest<Document, DocumentRepo> {

    @Value("classpath:mock/discovery/document")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Document.class;
    }

    @Override
    protected String getPath() {
        return "/documents";
    }

}
