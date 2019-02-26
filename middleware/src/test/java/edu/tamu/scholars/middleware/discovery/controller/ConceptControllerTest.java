package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Concept;
import edu.tamu.scholars.middleware.discovery.model.repo.ConceptRepo;

public class ConceptControllerTest extends AbstractSolrDocumentControllerTest<Concept, ConceptRepo> {

    @Value("classpath:mock/discovery/concept")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Concept.class;
    }

    @Override
    protected String getPath() {
        return "/concepts";
    }

}
