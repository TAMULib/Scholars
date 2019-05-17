package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDisplayTabView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DisplayTabViewTest {

    @Test
    public void testDefaultConstructor() {
        DisplayTabView tab = new DisplayTabView();
        assertNotNull(tab);
        assertNotNull(tab.getSections());
        assertEquals(0, tab.getSections().size());
        assertFalse(tab.isHidden());

    }

    @Test
    public void testGettersAndSetters() {
        DisplayTabView tab = getMockDisplayTabView();
        tab.setId(1L);

        assertEquals(1L, tab.getId(), 1);
        assertEquals("Test", tab.getName());
        assertFalse(tab.isHidden());

        assertEquals(1, tab.getSections().size());
        assertFalse(tab.getSections().get(0).isHidden());
        assertEquals("Test", tab.getSections().get(0).getName());
        assertEquals("<span>Hello, World!</span>", tab.getSections().get(0).getTemplate());
    }

}
