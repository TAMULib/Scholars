package edu.tamu.scholars.middleware.discovery.model.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.discovery.AbstractSolrDocumentIntegrationTest;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
