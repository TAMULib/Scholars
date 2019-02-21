package edu.tamu.scholars.middleware.discovery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.SolrDocumentRepo;

public abstract class AbstractSolrDocumentIntegrationTest<D extends AbstractSolrDocument, R extends SolrDocumentRepo<D>> {

    @Value("classpath:solr/discovery")
    private Resource instanceDirectory;

    @Autowired
    private EmbeddedSolrServer solrServer;

    @Autowired
    protected R repo;

    protected List<D> mockDocuments;

    @BeforeEach
    public void createCore() throws SolrServerException, IOException {
        assertTrue(instanceDirectory.exists());
        assertTrue(instanceDirectory.isFile());
        CoreAdminRequest.createCore(getCollection(), instanceDirectory.getFile().getAbsolutePath(), solrServer);
    }

    @BeforeEach
    public void setDocuments() throws IOException {
        mockDocuments = new ArrayList<D>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (File file : getMockFiles()) {
            if (file.isFile()) {
                @SuppressWarnings("unchecked")
                D mockDocument = (D) objectMapper.readValue(file, getType());
                assertNotNull(mockDocument);
                mockDocuments.add(mockDocument);
            }
        }
        assertTrue(mockDocuments.size() > 0);
    }

    @AfterEach
    public void deleteCore() throws SolrServerException, IOException {
        CoreAdminRequest.unloadCore(getCollection(), solrServer);
    }

    @AfterEach
    public void deleteDocuments() {
        repo.deleteAll();
    }

    protected void createDocuments() {
        assertEquals(0, repo.count());
        mockDocuments.forEach(mockDocument -> {
            repo.save(mockDocument);
        });
        assertEquals(mockDocuments.size(), repo.count());
    }

    private File[] getMockFiles() throws IOException {
        Resource mocksDirectoryResource = getMocksDirectory();
        assertTrue(mocksDirectoryResource.exists());
        assertTrue(mocksDirectoryResource.isFile());
        File mocksDirectory = mocksDirectoryResource.getFile();
        assertTrue(mocksDirectory.isDirectory());
        return mocksDirectory.listFiles();
    }

    private String getCollection() {
        SolrDocument solrDocument = getType().getAnnotation(SolrDocument.class);
        String collection = solrDocument.collection();
        assertFalse(collection.isEmpty());
        return collection;
    }

    protected abstract Resource getMocksDirectory();

    protected abstract Class<?> getType();

}
