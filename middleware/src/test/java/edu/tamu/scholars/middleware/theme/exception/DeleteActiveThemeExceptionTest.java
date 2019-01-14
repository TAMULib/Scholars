package edu.tamu.scholars.middleware.theme.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DeleteActiveThemeExceptionTest {

    @Test
    public void testDefaultConstructor() {
        DeleteActiveThemeException exception = new DeleteActiveThemeException("Test delete active theme exception!");
        assertNotNull(exception);
        assertEquals("Test delete active theme exception!", exception.getMessage());
    }

    @Test(expected = DeleteActiveThemeException.class)
    public void testThrow() throws DeleteActiveThemeException {
        throw new DeleteActiveThemeException("Test delete active theme exception!");
    }

}