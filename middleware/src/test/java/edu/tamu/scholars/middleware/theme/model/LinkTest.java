package edu.tamu.scholars.middleware.theme.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LinkTest {

    @Test
    public void testDefaultConstructor() {
        Link link = new Link();
        assertNotNull(link);
    }

    @Test
    public void testBasicConstructor() {
        Link link = new Link("Home", "http://localhost:4200");
        assertNotNull(link);
        assertEquals("Home", link.getLabel());
        assertEquals("http://localhost:4200", link.getUri());
    }

    @Test
    public void testGettersAndSetters() {
        Link link = new Link();
        link.setLabel("Home");
        link.setUri("http://localhost:4200");
        assertEquals("Home", link.getLabel());
        assertEquals("http://localhost:4200", link.getUri());
    }

}
