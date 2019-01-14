package edu.tamu.scholars.middleware.auth.controller.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import edu.tamu.scholars.middleware.auth.controller.exception.RegistrationException;

@RunWith(SpringRunner.class)
public class RegistrationExceptionTest {

    @Test
    public void testDefaultConstructor() {
        RegistrationException exception = new RegistrationException("Test registration exception!");
        assertNotNull(exception);
        assertEquals("Test registration exception!", exception.getMessage());
    }

    @Test(expected = RegistrationException.class)
    public void testThrow() throws RegistrationException {
        throw new RegistrationException("Test registration exception!");
    }

}
