package edu.tamu.scholars.middleware.theme.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StyleTest {

    @Test
    public void testDefaultConstructor() {
        Style style = new Style();
        assertNotNull(style);
    }

    @Test
    public void testBasicConstructor() {
        Style style = new Style("--variable", "test");
        assertNotNull(style);
        assertEquals("--variable", style.getKey());
        assertEquals("test", style.getValue());
    }

    @Test
    public void testGettersAndSetters() {
        Style style = new Style();
        style.setKey("--variable");
        style.setValue("test");
        assertEquals("--variable", style.getKey());
        assertEquals("test", style.getValue());
    }

}
