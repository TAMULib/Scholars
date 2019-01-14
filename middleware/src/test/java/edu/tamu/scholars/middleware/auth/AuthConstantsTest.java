package edu.tamu.scholars.middleware.auth;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthConstantsTest {

    @Test
    public void testDefaultConstructor() {
        AuthConstants authConstants = new AuthConstants();
        assertNotNull("Auth constants could not construct", authConstants);
    }

}
