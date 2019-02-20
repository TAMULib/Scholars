package edu.tamu.scholars.middleware.discovery.model.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

public class RelationshipRepoTest extends AbstractSolrDocumentRepoTest<Relationship, RelationshipRepo> {

    @Value("classpath:mock/discovery/relationship")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Relationship.class;
    }

}
