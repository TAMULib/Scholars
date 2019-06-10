package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Collection;
import edu.tamu.scholars.middleware.discovery.model.repo.CollectionRepo;

public class CollectionControllerTest extends AbstractSolrDocumentControllerTest<Collection, CollectionRepo> {

    @Value("classpath:mock/discovery/collection")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Collection.class;
    }

    @Override
    protected String getPath() {
        return "/collections";
    }

}
