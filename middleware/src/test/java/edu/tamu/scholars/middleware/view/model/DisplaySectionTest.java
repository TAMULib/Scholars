package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DisplaySectionTest {

    @Test
    public void testDefaultConstructor() {
        DisplaySection displaySection = new DisplaySection();
        assertNotNull(displaySection);
        assertFalse(displaySection.isHidden());
    }

    @Test
    public void testGettersAndSetters() {
        DisplaySection displaySection = new DisplaySection();

        displaySection.setName("Test");
        assertEquals("Test", displaySection.getName());

        displaySection.setHidden(true);
        assertTrue(displaySection.isHidden());

        displaySection.setTemplate("<span>Hello, World!</span>");
        assertEquals("<span>Hello, World!</span>", displaySection.getTemplate());
    }

}
