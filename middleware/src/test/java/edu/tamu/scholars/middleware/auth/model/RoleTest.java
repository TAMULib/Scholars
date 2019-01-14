package edu.tamu.scholars.middleware.auth.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
