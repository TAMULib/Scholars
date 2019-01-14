package edu.tamu.scholars.middleware;

import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_DURATION_IN_DAYS;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MAX_LENGTH;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MIN_LENGTH;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;
import edu.tamu.scholars.middleware.auth.config.PasswordConfig;
import edu.tamu.scholars.middleware.auth.config.TokenConfig;
import edu.tamu.scholars.middleware.config.MailConfig;
import edu.tamu.scholars.middleware.config.MiddlewareConfig;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MiddlewareApplicationTest {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private MiddlewareConfig middlewareConfig;

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    private MailConfig mailConfig;

    @Test
    public void contextLoads() {
        assertEquals("test", activeProfile);
    }

    @Test
    public void testStaticConstants() {
        assertEquals(180, PASSWORD_DURATION_IN_DAYS);
        assertEquals(8, PASSWORD_MIN_LENGTH);
        assertEquals(64, PASSWORD_MAX_LENGTH);
    }

    @Test
    public void testMiddlewareConfig() {
        AuthConfig authConfig = middlewareConfig.getAuth();
        assertEquals(14, authConfig.getRegistrationTokenDuration());
        PasswordConfig passwordConfig = authConfig.getPassword();
        assertEquals(180, passwordConfig.getDuration());
        assertEquals(8, passwordConfig.getMinLength());
        assertEquals(64, passwordConfig.getMaxLength());
        TokenConfig tokenConfig = authConfig.getToken();
        assertEquals(1, tokenConfig.getServerInteger());
        assertEquals("wKFkxTX54UzKx6xCYnC8WlEI2wtOy0PR", tokenConfig.getServerSecret());
        assertEquals(64, tokenConfig.getPseudoRandomNumberBytes());
        MailConfig mailConfig = middlewareConfig.getMail();
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getFrom());
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getReplyTo());
    }

    @Test
    public void testAuthConfig() {
        PasswordConfig passwordConfig = authConfig.getPassword();
        assertEquals(14, authConfig.getRegistrationTokenDuration());
        assertEquals(180, passwordConfig.getDuration());
        assertEquals(8, passwordConfig.getMinLength());
        assertEquals(64, passwordConfig.getMaxLength());
        TokenConfig tokenConfig = authConfig.getToken();
        assertEquals(1, tokenConfig.getServerInteger());
        assertEquals("wKFkxTX54UzKx6xCYnC8WlEI2wtOy0PR", tokenConfig.getServerSecret());
        assertEquals(64, tokenConfig.getPseudoRandomNumberBytes());
    }

    @Test
    public void testPasswordConfig() {
        assertEquals(180, passwordConfig.getDuration());
        assertEquals(8, passwordConfig.getMinLength());
        assertEquals(64, passwordConfig.getMaxLength());
    }

    @Test
    public void testTokenConfig() {
        assertEquals(1, tokenConfig.getServerInteger());
        assertEquals("wKFkxTX54UzKx6xCYnC8WlEI2wtOy0PR", tokenConfig.getServerSecret());
        assertEquals(64, tokenConfig.getPseudoRandomNumberBytes());
    }

    @Test
    public void testMailConfig() {
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getFrom());
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getReplyTo());
    }

    @Test
    public void testMain() {
        MiddlewareApplication.main(new String[0]);
    }

}
