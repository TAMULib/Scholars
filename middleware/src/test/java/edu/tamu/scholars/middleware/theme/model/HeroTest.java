package edu.tamu.scholars.middleware.theme.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class HeroTest {

    @Test
    public void testDefaultConstructor() {
        Hero hero = new Hero();
        assertNotNull(hero);
    }

    @Test
    public void testGettersAndSetters() {
        Hero hero = new Hero();
        hero.setImageUri("/assets/images/hero.png");
        hero.setImageAlt("Hero");
        hero.setWatermarkImageUri("/assets/images/watermark.png");
        hero.setWatermarkText("Watermark");
        hero.setBaseText("This is only a test!");
        hero.setFontColor("#ffffff");
        hero.setLinkColor("#000000");
        hero.setLinkHoverColor("#ffc222");
        hero.setSlideInterval(1500);

        assertEquals("/assets/images/hero.png", hero.getImageUri());
        assertEquals("Hero", hero.getImageAlt());
        assertEquals("/assets/images/watermark.png", hero.getWatermarkImageUri());
        assertEquals("Watermark", hero.getWatermarkText());
        assertEquals("This is only a test!", hero.getBaseText());
        assertEquals("#ffffff", hero.getFontColor());
        assertEquals("#000000", hero.getLinkColor());
        assertEquals("#ffc222", hero.getLinkHoverColor());
        assertEquals(1500, hero.getSlideInterval());
    }

}
