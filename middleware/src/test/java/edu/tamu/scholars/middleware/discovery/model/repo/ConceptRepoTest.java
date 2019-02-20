package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Concept;

public class ConceptRepoTest extends AbstractSolrDocumentRepoTest<Concept, ConceptRepo> {

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

}
