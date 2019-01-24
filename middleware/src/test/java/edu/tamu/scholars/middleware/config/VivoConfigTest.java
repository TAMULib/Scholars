package edu.tamu.scholars.middleware.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VivoConfigTest {

    @Test
    public void testDefaultConstructor() {
        VivoConfig vivoConfig = new VivoConfig();
        assertNotNull(vivoConfig);
        assertEquals("vivo_root@school.edu", vivoConfig.getEmail());
        assertEquals("rootPassword", vivoConfig.getPassword());
        assertEquals("http://localhost:8080/vivo", vivoConfig.getBaseUrl());
        assertEquals("/individual", vivoConfig.getLinkedOpenDataEndpoint());
        assertEquals("http://localhost:8080/vivo/individual", vivoConfig.getLinkedOpenDataEndpointUrl());
        assertEquals("/listrdf", vivoConfig.getListRdfEndpoint());
        assertEquals("http://localhost:8080/vivo/listrdf", vivoConfig.getListRdfEndpointUrl());
        assertEquals("/api/sparqlQuery", vivoConfig.getSparqlQueryEndpoint());
        assertEquals("http://localhost:8080/vivo/api/sparqlQuery", vivoConfig.getSparqlQueryEndpointUrl());
    }

    @Test
    public void testGettersAndSetters() {
        VivoConfig vivoConfig = new VivoConfig();
        vivoConfig.setEmail("vivoadmin@tamu.edu");
        vivoConfig.setPassword("abc123");
        vivoConfig.setBaseUrl("http://scholars.library.edu");
        vivoConfig.setLinkedOpenDataEndpoint("/individual");
        vivoConfig.setListRdfEndpoint("/listrdf");
        vivoConfig.setSparqlQueryEndpoint("/api/sparqlQuery");
        assertEquals("vivoadmin@tamu.edu", vivoConfig.getEmail());
        assertEquals("abc123", vivoConfig.getPassword());
        assertEquals("http://scholars.library.edu", vivoConfig.getBaseUrl());
        assertEquals("/individual", vivoConfig.getLinkedOpenDataEndpoint());
        assertEquals("http://scholars.library.edu/individual", vivoConfig.getLinkedOpenDataEndpointUrl());
        assertEquals("/listrdf", vivoConfig.getListRdfEndpoint());
        assertEquals("http://scholars.library.edu/listrdf", vivoConfig.getListRdfEndpointUrl());
        assertEquals("/api/sparqlQuery", vivoConfig.getSparqlQueryEndpoint());
        assertEquals("http://scholars.library.edu/api/sparqlQuery", vivoConfig.getSparqlQueryEndpointUrl());
    }

}
