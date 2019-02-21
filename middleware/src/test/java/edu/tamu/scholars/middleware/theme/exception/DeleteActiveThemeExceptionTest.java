package edu.tamu.scholars.middleware.theme.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DeleteActiveThemeExceptionTest {

    @Test
    public void testDefaultConstructor() {
        DeleteActiveThemeException exception = new DeleteActiveThemeException("Test delete active theme exception!");
        assertNotNull(exception);
        assertEquals("Test delete active theme exception!", exception.getMessage());
    }

    @Test
    public void testThrow() throws DeleteActiveThemeException {
        assertThrows(DeleteActiveThemeException.class, () -> {
            throw new DeleteActiveThemeException("Test delete active theme exception!");
        });
    }

}