package edu.tamu.scholars.middleware.view.model;

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
public class DisplayTabSectionViewTest {

    @Test
    public void testDefaultConstructor() {
        DisplayTabSectionView section = new DisplayTabSectionView();
        assertNotNull(section);
        assertFalse(section.isHidden());
        assertEquals(0, section.getRequiredFields().size());
    }

    @Test
    public void testGettersAndSetters() {
        DisplayTabSectionView section = new DisplayTabSectionView();

        section.setName("Test");
        assertEquals("Test", section.getName());

        section.setHidden(true);
        assertTrue(section.isHidden());

        section.setTemplate("<span>Hello, World!</span>");
        assertEquals("<span>Hello, World!</span>", section.getTemplate());

        List<String> requiredFields = new ArrayList<String>();
        requiredFields.add("type");

        section.setRequiredFields(requiredFields);
        assertEquals(1, section.getRequiredFields().size());
        assertEquals("type", section.getRequiredFields().get(0));
    }

}
