package edu.tamu.scholars.middleware.auth.controller.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RegistrationTest {

    @Test
    public void testDefaultConstructor() {
        Registration registration = new Registration();
        assertNotNull(registration);
    }

    @Test
    public void testGettersAndSetters() {
        Registration registration = new Registration();
        registration.setFirstName("Bob");
        registration.setLastName("Boring");
        registration.setEmail("bboring@mailinator.com");
        registration.setPassword("HelloWorld123!");
        registration.setConfirm("HelloWorld123!");
        assertEquals("Bob", registration.getFirstName());
        assertEquals("Boring", registration.getLastName());
        assertEquals("bboring@mailinator.com", registration.getEmail());
        assertEquals("HelloWorld123!", registration.getPassword());
        assertEquals("HelloWorld123!", registration.getConfirm());
    }

}
