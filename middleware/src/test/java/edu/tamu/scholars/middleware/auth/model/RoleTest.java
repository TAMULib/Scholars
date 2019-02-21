package edu.tamu.scholars.middleware.auth.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RoleTest {

    @Test
    public void testGetValue() {
        assertEquals("User", Role.ROLE_USER.getValue());
        assertEquals("Administrator", Role.ROLE_ADMIN.getValue());
        assertEquals("Super Administrator", Role.ROLE_SUPER_ADMIN.getValue());
    }

    @Test
    public void testWithValue() {
        assertEquals(Role.ROLE_USER, Role.withValue("User"));
        assertEquals(Role.ROLE_ADMIN, Role.withValue("Administrator"));
        assertEquals(Role.ROLE_SUPER_ADMIN, Role.withValue("Super Administrator"));
    }

}
