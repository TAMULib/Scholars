package edu.tamu.scholars.middleware.messaging;

import static edu.tamu.scholars.middleware.messaging.EntityAction.CREATE;
import static edu.tamu.scholars.middleware.messaging.EntityAction.DELETE;
import static edu.tamu.scholars.middleware.messaging.EntityAction.UPDATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.theme.model.Theme;

@ExtendWith(SpringExtension.class)
public class EntityMessageTest {

    @Test
    public void testCreateEntityMessage() {
        CreateEntityMessage<Theme> createThemeMessage = new CreateEntityMessage<Theme>(new Theme("Test", "Testing Unlimited"));
        assertNotNull(createThemeMessage);
        assertEquals("Test", createThemeMessage.getEntity().getName());
        assertEquals("Testing Unlimited", createThemeMessage.getEntity().getOrganization());
        assertEquals(CREATE, createThemeMessage.getAction());
    }

    @Test
    public void testUpdateEntityMessage() {
        UpdateEntityMessage<Theme> updateThemeMessage = new UpdateEntityMessage<Theme>(new Theme("Test", "Testing Unlimited"));
        assertNotNull(updateThemeMessage);
        assertEquals("Test", updateThemeMessage.getEntity().getName());
        assertEquals("Testing Unlimited", updateThemeMessage.getEntity().getOrganization());
        assertEquals(UPDATE, updateThemeMessage.getAction());
    }

    @Test
    public void testDeleteMessage() {
        DeleteEntityMessage<String> deleteThemeMessage = new DeleteEntityMessage<String>("Test");
        assertNotNull(deleteThemeMessage);
        assertEquals("Test", deleteThemeMessage.getIdentifier());
        assertEquals(DELETE, deleteThemeMessage.getAction());
    }

}
