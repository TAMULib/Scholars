package edu.tamu.scholars.middleware.discovery.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RelationshipTest extends AbstractSolrDocumentTest<Relationship> {

    @Override
    public Class<?> getType() {
        return Relationship.class;
    }

}
