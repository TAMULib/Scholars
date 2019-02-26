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
public class HeaderTest {

    @Test
    public void testDefaultConstructor() {
        Header header = new Header();
        assertNotNull(header);
        assertNotNull(header.getNavbar());
        assertNotNull(header.getBanner());
        assertNotNull(header.getVariables());
        assertTrue(header.getVariables().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        Header header = new Header();

        Navbar navbar = new Navbar();
        navbar.setBrandText("Hello, World!");
        navbar.setBrandUri("http://localhost:4200");
        navbar.setLogoUri("/assets/images/logo.png");

        List<Link> navbarLinks = new ArrayList<Link>();
        navbarLinks.add(new Link("Home", "http://localhost:4200"));

        navbar.setLinks(navbarLinks);

        List<Style> navbarVairables = new ArrayList<Style>();
        navbarVairables.add(new Style("--test", "navbar"));

        navbar.setVariables(navbarVairables);

        header.setNavbar(navbar);

        assertEquals("Hello, World!", header.getNavbar().getBrandText());
        assertEquals("http://localhost:4200", header.getNavbar().getBrandUri());
        assertEquals("/assets/images/logo.png", header.getNavbar().getLogoUri());
        assertEquals(1, header.getNavbar().getLinks().size());
        assertEquals("Home", header.getNavbar().getLinks().get(0).getLabel());
        assertEquals("http://localhost:4200", header.getNavbar().getLinks().get(0).getUri());
        assertEquals(1, header.getNavbar().getVariables().size());
        assertEquals("--test", header.getNavbar().getVariables().get(0).getKey());
        assertEquals("navbar", header.getNavbar().getVariables().get(0).getValue());

        Banner banner = new Banner();
        banner.setImageUri("/assets/images/banner.png");
        banner.setAltText("Test");

        List<Style> bannerVairables = new ArrayList<Style>();
        bannerVairables.add(new Style("--test", "banner"));

        banner.setVariables(bannerVairables);

        header.setBanner(banner);

        assertEquals("/assets/images/banner.png", header.getBanner().getImageUri());
        assertEquals("Test", header.getBanner().getAltText());
        assertEquals(1, header.getBanner().getVariables().size());
        assertEquals("--test", header.getBanner().getVariables().get(0).getKey());
        assertEquals("banner", header.getBanner().getVariables().get(0).getValue());

        List<Style> headerVairables = new ArrayList<Style>();
        headerVairables.add(new Style("--test", "header"));
        headerVairables.add(new Style("--variable", "test"));

        header.setVariables(headerVairables);

        assertEquals(2, header.getVariables().size());

        assertEquals("--test", header.getVariables().get(0).getKey());
        assertEquals("header", header.getVariables().get(0).getValue());
        assertEquals("--variable", header.getVariables().get(1).getKey());
        assertEquals("test", header.getVariables().get(1).getValue());
    }

}
