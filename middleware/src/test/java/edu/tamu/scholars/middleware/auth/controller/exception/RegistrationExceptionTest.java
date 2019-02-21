package edu.tamu.scholars.middleware.auth.controller.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RegistrationExceptionTest {

    @Test
    public void testDefaultConstructor() {
        RegistrationException exception = new RegistrationException("Test registration exception!");
        assertNotNull(exception);
        assertEquals("Test registration exception!", exception.getMessage());
    }

    @Test
    public void testThrow() throws RegistrationException {
        assertThrows(RegistrationException.class, () -> {
            throw new RegistrationException("Test registration exception!");
        });
    }

}
