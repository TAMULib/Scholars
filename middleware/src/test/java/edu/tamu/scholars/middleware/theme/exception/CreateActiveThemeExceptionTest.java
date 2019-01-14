package edu.tamu.scholars.middleware.theme.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreateActiveThemeExceptionTest {

    @Test
    public void testDefaultConstructor() {
        CreateActiveThemeException exception = new CreateActiveThemeException("Test create active theme exception!");
        assertNotNull(exception);
        assertEquals("Test create active theme exception!", exception.getMessage());
    }

    @Test(expected = CreateActiveThemeException.class)
    public void testThrow() throws CreateActiveThemeException {
        throw new CreateActiveThemeException("Test create active theme exception!");
    }

}