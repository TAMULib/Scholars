package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockTabView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TabViewTest {

    @Test
    public void testDefaultConstructor() {
        TabView tabView = new TabView();
        assertNotNull(tabView);
        assertNotNull(tabView.getSections());
        assertEquals(0, tabView.getSections().size());
        assertFalse(tabView.isHidden());

    }

    @Test
    public void testGettersAndSetters() {
        TabView tabView = getMockTabView();
        tabView.setId(1L);

        assertEquals(1L, tabView.getId(), 1);
        assertEquals("Test", tabView.getName());
        assertFalse(tabView.isHidden());

        assertEquals(1, tabView.getSections().size());
        assertFalse(tabView.getSections().get(0).isHidden());
        assertEquals("Test", tabView.getSections().get(0).getName());
        assertEquals("<span>Hello, World!</span>", tabView.getSections().get(0).getTemplate());
    }

}
