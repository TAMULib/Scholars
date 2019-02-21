package edu.tamu.scholars.middleware.theme.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FooterTest {

    @Test
    public void testDefaultConstructor() {
        Footer footer = new Footer();
        assertNotNull(footer);
        assertNotNull(footer.getLinks());
        assertNotNull(footer.getVariables());
        assertTrue(footer.getLinks().isEmpty());
        assertTrue(footer.getVariables().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        Footer footer = new Footer();
        List<Link> footerLinks = new ArrayList<Link>();
        footerLinks.add(new Link("About", "http://localhost:4200/about"));
        footerLinks.add(new Link("Test", "http://localhost:4200/test"));

        footer.setLinks(footerLinks);

        assertEquals(2, footer.getLinks().size());

        assertEquals("About", footer.getLinks().get(0).getLabel());
        assertEquals("http://localhost:4200/about", footer.getLinks().get(0).getUri());
        assertEquals("Test", footer.getLinks().get(1).getLabel());
        assertEquals("http://localhost:4200/test", footer.getLinks().get(1).getUri());

        List<Style> footerVairables = new ArrayList<Style>();
        footerVairables.add(new Style("--test", "footer"));
        footerVairables.add(new Style("--variable", "test"));

        footer.setVariables(footerVairables);

        assertEquals(2, footer.getVariables().size());

        assertEquals("--test", footer.getVariables().get(0).getKey());
        assertEquals("footer", footer.getVariables().get(0).getValue());
        assertEquals("--variable", footer.getVariables().get(1).getKey());
        assertEquals("test", footer.getVariables().get(1).getValue());
    }

}
