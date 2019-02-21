package edu.tamu.scholars.middleware.theme.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
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
