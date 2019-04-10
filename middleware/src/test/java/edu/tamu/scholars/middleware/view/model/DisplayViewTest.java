package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.MOCK_VIEW_NAME;
import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDisplayView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DisplayViewTest {

    @Test
    public void testDefaultConstructor() {
        DisplayView displayView = new DisplayView();
        assertNotNull(displayView);
        assertNotNull(displayView.getTabs());
    }

    @Test
    public void testGettersAndSetters() {
        DisplayView displayView = getMockDisplayView();
        displayView.setId(1L);

        assertEquals(1L, displayView.getId(), 1);
        assertEquals(MOCK_VIEW_NAME, displayView.getName());

        assertEquals("<div>Main</div>", displayView.getMainContentTemplate());
        assertEquals("<div>Left Scan</div>", displayView.getLeftScanTemplate());
        assertEquals("<div>Right Scan</div>", displayView.getRightScanTemplate());

        assertEquals(1, displayView.getTypes().size());
        assertEquals("FacultyMember", displayView.getTypes().get(0));

        assertEquals(1, displayView.getTabs().size());
        assertFalse(displayView.getTabs().get(0).isHidden());
        assertEquals("Test", displayView.getTabs().get(0).getName());
        assertEquals(1, displayView.getTabs().get(0).getSections().size());

        assertFalse(displayView.getTabs().get(0).getSections().get(0).isHidden());
        assertEquals("Test", displayView.getTabs().get(0).getSections().get(0).getName());
        assertEquals("<span>Hello, World!</span>", displayView.getTabs().get(0).getSections().get(0).getTemplate());
    }

}
