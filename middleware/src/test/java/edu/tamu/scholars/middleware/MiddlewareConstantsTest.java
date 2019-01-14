package edu.tamu.scholars.middleware;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MiddlewareConstantsTest {

    @Test
    public void testDefaultConstructor() {
        MiddlewareConstants middlewareConstants = new MiddlewareConstants();
        assertNotNull(middlewareConstants);
    }

}
