package edu.tamu.scholars.middleware.discovery.model.repo;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.tamu.scholars.middleware.discovery.AbstractSolrDocumentIntegrationTest;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractSolrDocumentRepoTest<D extends AbstractSolrDocument, R extends SolrDocumentRepo<D>> extends AbstractSolrDocumentIntegrationTest<D, R> {

    @Test
    public void testCreate() {
        createDocuments();
    }

    @Test
    public void testRead() throws IOException {
        createDocuments();
        mockDocuments.forEach(mockDocument -> {
            String id = mockDocument.getId();
            Optional<D> document = repo.findById(id);
            assertTrue(document.isPresent());
        });
    }

}
