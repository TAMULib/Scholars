package edu.tamu.scholars.middleware.theme;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.theme.model.Banner;
import edu.tamu.scholars.middleware.theme.model.Footer;
import edu.tamu.scholars.middleware.theme.model.Header;
import edu.tamu.scholars.middleware.theme.model.Hero;
import edu.tamu.scholars.middleware.theme.model.Home;
import edu.tamu.scholars.middleware.theme.model.Link;
import edu.tamu.scholars.middleware.theme.model.Navbar;
import edu.tamu.scholars.middleware.theme.model.Style;
import edu.tamu.scholars.middleware.theme.model.Theme;
import edu.tamu.scholars.middleware.theme.model.repo.ThemeRepo;

public abstract class ThemeIntegrationTest extends UserIntegrationTest {

    @Autowired
    protected ThemeRepo themeRepo;

    protected Theme getMockTheme() {
        Theme theme = new Theme("Test", "Testing Unlimited");

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

        home.setVariables(homeVairables);

        theme.setHome(home);

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

        Footer footer = new Footer();

        List<Link> footerLinks = new ArrayList<Link>();
        footerLinks.add(new Link("About", "http://localhost:4200/about"));

        footer.setLinks(footerLinks);

        List<Style> footerVairables = new ArrayList<Style>();
        footerVairables.add(new Style("--test", "footer"));

        footer.setVariables(footerVairables);

        theme.setFooter(footer);

        List<Style> colors = new ArrayList<Style>();
        colors.add(new Style("--red", "#ff0000"));
        colors.add(new Style("--green", "#00ff00"));
        colors.add(new Style("--blue", "#0000ff"));

        theme.setColors(colors);

        List<Style> variants = new ArrayList<Style>();
        variants.add(new Style("--primary", "#007bff"));
        variants.add(new Style("--secondary", "#6c757d"));
        variants.add(new Style("--success", "#28a745"));

        theme.setVariants(variants);

        List<Style> variables = new ArrayList<Style>();
        variables.add(new Style("--accent", "#ffc222"));
        variables.add(new Style("--navigation-color", "#3c0000"));
        variables.add(new Style("--navbar-color", "#ffffff"));

        theme.setVariables(variables);

        return theme;
    }

    @After
    public void deleteAllThemes() {
        themeRepo.deleteAll();
    }

}
