package edu.tamu.scholars.middleware.auth.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserTest {

    @Test
    public void testDefaultConstructor() {
        User user = new User();
        assertNotNull(user);
        assertTrue(user.getOldPasswords().isEmpty());
        assertEquals(Role.ROLE_USER, user.getRole());
        assertFalse(user.isConfirmed());
        assertTrue(user.isActive());
        assertFalse(user.isEnabled());
    }

    @Test
    public void testBasicConstructor() {
        User user = new User("Bob", "Boring", "bboring@mailinator.com");
        assertNotNull(user);
        assertEquals("Bob", user.getFirstName());
        assertEquals("Boring", user.getLastName());
        assertEquals("bboring@mailinator.com", user.getEmail());
        assertTrue(user.getOldPasswords().isEmpty());
        assertEquals(Role.ROLE_USER, user.getRole());
        assertFalse(user.isConfirmed());
        assertTrue(user.isActive());
        assertFalse(user.isEnabled());
    }

    @Test
    public void testCreateFromUser() {
        List<String> oldPasswords = new ArrayList<String>(Arrays.asList(new String[] { "$2y$04$BQnhPFasttVxAva5XEFSEu2rH/7GoqChxVQ7zxtDgUDQ8k4qFOzYe", "$2y$04$1GbY8kcFY8hDhsedNGYFduocc7/v.7T2mkapUKvxoIpBCSU8fj51u" }));
        Calendar timestamp = Calendar.getInstance();

        User user = new User();
        user.setId(1L);
        user.setFirstName("Bob");
        user.setLastName("Boring");
        user.setEmail("bboring@mailinator.com");
        user.setPassword("$2y$04$BQnhPFasttVxAva5XEFSEu2rH/7GoqChxVQ7zxtDgUDQ8k4qFOzYe");
        user.setOldPasswords(oldPasswords);
        user.setRole(Role.ROLE_ADMIN);
        user.setTimestamp(timestamp);
        user.setConfirmed(true);
        user.setActive(false);
        user.setEnabled(true);

        User derivedUser = new User(user);
        assertEquals(user.getId(), derivedUser.getId());
        assertEquals(user.getFirstName(), derivedUser.getFirstName());
        assertEquals(user.getLastName(), derivedUser.getLastName());
        assertEquals(user.getEmail(), derivedUser.getEmail());
        assertEquals(user.getPassword(), derivedUser.getPassword());
        assertEquals(user.getOldPasswords(), derivedUser.getOldPasswords());
        assertEquals(user.getRole(), derivedUser.getRole());
        assertEquals(user.getTimestamp(), derivedUser.getTimestamp());
        assertEquals(user.isActive(), derivedUser.isActive());
        assertEquals(user.isEnabled(), derivedUser.isEnabled());
    }

}
