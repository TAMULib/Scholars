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
public class HomeTest {

    @Test
    public void testDefaultConstructor() {
        Home home = new Home();
        assertNotNull(home);
        assertNotNull(home.getVariables());
        assertFalse(home.isHeroesNavigable());
        assertTrue(home.getVariables().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        Home home = new Home();

        home.setHeroesNavigable(true);

        assertTrue(home.isHeroesNavigable());

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

        assertEquals(1, home.getHeroes().size());

        assertEquals("/assets/images/hero.png", home.getHeroes().get(0).getImageUri());
        assertEquals("Hero", home.getHeroes().get(0).getImageAlt());
        assertEquals("/assets/images/watermark.png", home.getHeroes().get(0).getWatermarkImageUri());
        assertEquals("Watermark", home.getHeroes().get(0).getWatermarkText());
        assertEquals("This is only a test!", home.getHeroes().get(0).getBaseText());
        assertEquals("#ffffff", home.getHeroes().get(0).getFontColor());
        assertEquals("#000000", home.getHeroes().get(0).getLinkColor());
        assertEquals("#ffc222", home.getHeroes().get(0).getLinkHoverColor());
        assertEquals(5000, home.getHeroes().get(0).getInterval());

        List<Style> homeVairables = new ArrayList<Style>();
        homeVairables.add(new Style("--test", "home"));
        homeVairables.add(new Style("--variable", "test"));

        home.setVariables(homeVairables);

        assertEquals(2, home.getVariables().size());

        assertEquals("--test", home.getVariables().get(0).getKey());
        assertEquals("home", home.getVariables().get(0).getValue());
        assertEquals("--variable", home.getVariables().get(1).getKey());
        assertEquals("test", home.getVariables().get(1).getValue());
    }

}
