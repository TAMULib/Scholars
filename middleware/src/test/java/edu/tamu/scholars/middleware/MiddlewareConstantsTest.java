package edu.tamu.scholars.middleware;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MiddlewareConstantsTest {

    @Test
    public void testDefaultConstructor() {
        MiddlewareConstants middlewareConstants = new MiddlewareConstants();
        assertNotNull(middlewareConstants);
    }

}
