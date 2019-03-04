package edu.tamu.scholars.middleware.theme.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ThemeTest {

    @Test
    public void testDefaultConstructor() {
        Theme theme = new Theme();
        assertNotNull(theme);
        assertFalse(theme.isActive());
        assertNotNull(theme.getHome());
        assertNotNull(theme.getHeader());
        assertNotNull(theme.getFooter());
        assertNotNull(theme.getColors());
        assertNotNull(theme.getVariants());
        assertNotNull(theme.getVariables());
        assertTrue(theme.getColors().isEmpty());
        assertTrue(theme.getVariants().isEmpty());
        assertTrue(theme.getVariables().isEmpty());
    }

    @Test
    public void testBasicConstructor() {
        Theme theme = new Theme("Test", "Testing Unlimited");
        assertEquals("Test", theme.getName());
        assertEquals("Testing Unlimited", theme.getOrganization());
    }

    @Test
    public void testGettersAndSetters() {
        Theme theme = new Theme();

        theme.setId(1L);
        theme.setName("Test");
        theme.setOrganization("Testing Unlimited");

        assertEquals(1L, theme.getId(), 1);
        assertEquals("Test", theme.getName());
        assertEquals("Testing Unlimited", theme.getOrganization());

        Home home = new Home();

        List<Hero> heroes = new ArrayList<Hero>();
        Hero hero = new Hero();
        hero.setImageUri("/assets/images/hero.png");
        hero.setImageAlt("Hero");
        hero.setWatermarkImageUri("/assets/images/watermark.png");
        hero.setWatermarkText("Watermark");
        hero.setBaseText("This is only a test!");
        hero.setFontColor("#ffffff");
        hero.setLinkColor("#000000");
        hero.setLinkHoverColor("#ffc222");
        hero.setInterval(5000);

        heroes.add(hero);

        home.setHeroes(heroes);

        List<Style> homeVairables = new ArrayList<Style>();
        homeVairables.add(new Style("--test", "home"));
        homeVairables.add(new Style("--variable", "test"));

        home.setVariables(homeVairables);

        theme.setHome(home);

        assertEquals(1, home.getHeroes().size());

        assertEquals("/assets/images/hero.png", theme.getHome().getHeroes().get(0).getImageUri());
        assertEquals("Hero", theme.getHome().getHeroes().get(0).getImageAlt());
        assertEquals("/assets/images/watermark.png", theme.getHome().getHeroes().get(0).getWatermarkImageUri());
        assertEquals("Watermark", theme.getHome().getHeroes().get(0).getWatermarkText());
        assertEquals("This is only a test!", theme.getHome().getHeroes().get(0).getBaseText());
        assertEquals("#ffffff", theme.getHome().getHeroes().get(0).getFontColor());
        assertEquals("#000000", theme.getHome().getHeroes().get(0).getLinkColor());
        assertEquals("#ffc222", theme.getHome().getHeroes().get(0).getLinkHoverColor());
        assertEquals(5000, theme.getHome().getHeroes().get(0).getInterval());

        assertEquals(2, theme.getHome().getVariables().size());

        assertEquals("--test", theme.getHome().getVariables().get(0).getKey());
        assertEquals("home", theme.getHome().getVariables().get(0).getValue());
        assertEquals("--variable", theme.getHome().getVariables().get(1).getKey());
        assertEquals("test", theme.getHome().getVariables().get(1).getValue());

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

        Banner banner = new Banner();
        banner.setImageUri("/assets/images/banner.png");
        banner.setAltText("Test");

        List<Style> bannerVairables = new ArrayList<Style>();
        bannerVairables.add(new Style("--test", "banner"));

        banner.setVariables(bannerVairables);

        header.setBanner(banner);

        List<Style> headerVairables = new ArrayList<Style>();
        headerVairables.add(new Style("--test", "header"));

        header.setVariables(headerVairables);

        theme.setHeader(header);

        assertEquals("Hello, World!", theme.getHeader().getNavbar().getBrandText());
        assertEquals("http://localhost:4200", theme.getHeader().getNavbar().getBrandUri());
        assertEquals("/assets/images/logo.png", theme.getHeader().getNavbar().getLogoUri());
        assertEquals(1, theme.getHeader().getNavbar().getLinks().size());
        assertEquals("Home", theme.getHeader().getNavbar().getLinks().get(0).getLabel());
        assertEquals("http://localhost:4200", theme.getHeader().getNavbar().getLinks().get(0).getUri());
        assertEquals(1, theme.getHeader().getNavbar().getVariables().size());
        assertEquals("--test", theme.getHeader().getNavbar().getVariables().get(0).getKey());
        assertEquals("navbar", theme.getHeader().getNavbar().getVariables().get(0).getValue());

        assertEquals("/assets/images/banner.png", theme.getHeader().getBanner().getImageUri());
        assertEquals("Test", theme.getHeader().getBanner().getAltText());
        assertEquals(1, theme.getHeader().getBanner().getVariables().size());
        assertEquals("--test", theme.getHeader().getBanner().getVariables().get(0).getKey());
        assertEquals("banner", theme.getHeader().getBanner().getVariables().get(0).getValue());

        assertEquals(1, theme.getHeader().getVariables().size());

        assertEquals("--test", theme.getHeader().getVariables().get(0).getKey());
        assertEquals("header", theme.getHeader().getVariables().get(0).getValue());

        Footer footer = new Footer();

        List<Link> footerLinks = new ArrayList<Link>();
        footerLinks.add(new Link("About", "http://localhost:4200/about"));

        footer.setLinks(footerLinks);

        List<Style> footerVairables = new ArrayList<Style>();
        footerVairables.add(new Style("--test", "footer"));

        footer.setVariables(footerVairables);

        theme.setFooter(footer);

        assertEquals(1, theme.getFooter().getLinks().size());

        assertEquals("About", theme.getFooter().getLinks().get(0).getLabel());
        assertEquals("http://localhost:4200/about", theme.getFooter().getLinks().get(0).getUri());

        assertEquals(1, theme.getFooter().getVariables().size());

        assertEquals("--test", theme.getFooter().getVariables().get(0).getKey());
        assertEquals("footer", theme.getFooter().getVariables().get(0).getValue());

        List<Style> colors = new ArrayList<Style>();
        colors.add(new Style("--red", "#ff0000"));
        colors.add(new Style("--green", "#00ff00"));
        colors.add(new Style("--blue", "#0000ff"));

        theme.setColors(colors);

        assertEquals(3, theme.getColors().size());

        assertEquals("--red", theme.getColors().get(0).getKey());
        assertEquals("#ff0000", theme.getColors().get(0).getValue());
        assertEquals("--green", theme.getColors().get(1).getKey());
        assertEquals("#00ff00", theme.getColors().get(1).getValue());
        assertEquals("--blue", theme.getColors().get(2).getKey());
        assertEquals("#0000ff", theme.getColors().get(2).getValue());

        List<Style> variants = new ArrayList<Style>();
        variants.add(new Style("--primary", "#007bff"));
        variants.add(new Style("--secondary", "#6c757d"));
        variants.add(new Style("--success", "#28a745"));

        theme.setVariants(variants);

        assertEquals(3, theme.getVariants().size());

        assertEquals("--primary", theme.getVariants().get(0).getKey());
        assertEquals("#007bff", theme.getVariants().get(0).getValue());
        assertEquals("--secondary", theme.getVariants().get(1).getKey());
        assertEquals("#6c757d", theme.getVariants().get(1).getValue());
        assertEquals("--success", theme.getVariants().get(2).getKey());
        assertEquals("#28a745", theme.getVariants().get(2).getValue());

        List<Style> variables = new ArrayList<Style>();
        variables.add(new Style("--accent", "#ffc222"));
        variables.add(new Style("--navigation-color", "#3c0000"));
        variables.add(new Style("--navbar-color", "#ffffff"));

        theme.setVariables(variables);

        assertEquals(3, theme.getVariables().size());

        assertEquals("--accent", theme.getVariables().get(0).getKey());
        assertEquals("#ffc222", theme.getVariables().get(0).getValue());
        assertEquals("--navigation-color", theme.getVariables().get(1).getKey());
        assertEquals("#3c0000", theme.getVariables().get(1).getValue());
        assertEquals("--navbar-color", theme.getVariables().get(2).getKey());
        assertEquals("#ffffff", theme.getVariables().get(2).getValue());
    }

}
