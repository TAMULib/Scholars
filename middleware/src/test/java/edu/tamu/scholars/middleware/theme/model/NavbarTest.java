package edu.tamu.scholars.middleware.theme.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NavbarTest {

    @Test
    public void testDefaultConstructor() {
        Navbar navbar = new Navbar();
        assertNotNull(navbar);
        assertNotNull(navbar.getLinks());
        assertNotNull(navbar.getVariables());
        assertTrue(navbar.getLinks().isEmpty());
        assertTrue(navbar.getVariables().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        Navbar navbar = new Navbar();

        navbar.setBrandText("Hello, World!");
        navbar.setBrandUri("http://localhost:4200");
        navbar.setLogoUri("/assets/images/logo.png");

        assertEquals("Hello, World!", navbar.getBrandText());
        assertEquals("http://localhost:4200", navbar.getBrandUri());
        assertEquals("/assets/images/logo.png", navbar.getLogoUri());

        List<Link> navbarLinks = new ArrayList<Link>();
        navbarLinks.add(new Link("About", "http://localhost:4200/about"));
        navbarLinks.add(new Link("Test", "http://localhost:4200/test"));

        navbar.setLinks(navbarLinks);

        assertEquals(2, navbar.getLinks().size());

        assertEquals("About", navbar.getLinks().get(0).getLabel());
        assertEquals("http://localhost:4200/about", navbar.getLinks().get(0).getUri());
        assertEquals("Test", navbar.getLinks().get(1).getLabel());
        assertEquals("http://localhost:4200/test", navbar.getLinks().get(1).getUri());

        List<Style> navbarVairables = new ArrayList<Style>();
        navbarVairables.add(new Style("--test", "navbar"));
        navbarVairables.add(new Style("--variable", "test"));

        navbar.setVariables(navbarVairables);

        assertEquals(2, navbar.getVariables().size());

        assertEquals("--test", navbar.getVariables().get(0).getKey());
        assertEquals("navbar", navbar.getVariables().get(0).getValue());
        assertEquals("--variable", navbar.getVariables().get(1).getKey());
        assertEquals("test", navbar.getVariables().get(1).getValue());
    }

}
