package edu.tamu.scholars.middleware.discovery.model.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.config.SolrTestConfig;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SolrTestConfig.class })
public abstract class AbstractSolrDocumentRepoTest<D extends AbstractSolrDocument, R extends SolrDocumentRepo<D>> {

    @Autowired
    private R repo;

    @Autowired
    private EmbeddedSolrServer solrServer;

    @Value("classpath:solr/discovery")
    private Resource instanceDirectory;

    private List<D> mockDocuments;

    @Before
    public void createCore() throws SolrServerException, IOException {
        assertTrue(instanceDirectory.exists());
        assertTrue(instanceDirectory.isFile());
        CoreAdminRequest.createCore(getCollection(), instanceDirectory.getFile().getAbsolutePath(), solrServer);
    }

    @Before
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

    @Test
    public void testCreate() {
        assertEquals(0, repo.count());
        mockDocuments.forEach(mockDocument -> {
            repo.save(mockDocument);
        });
        assertEquals(mockDocuments.size(), repo.count());
    }

    @Test
    public void testRead() throws IOException {
        testCreate();
        mockDocuments.forEach(mockDocument -> {
            String id = mockDocument.getId();
            Optional<D> document = repo.findById(id);
            assertTrue(document.isPresent());
        });
    }

    @After
    public void deleteCore() throws SolrServerException, IOException {
        CoreAdminRequest.unloadCore(getCollection(), solrServer);
    }

    @After
    public void deleteDocuments() {
        repo.deleteAll();
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
