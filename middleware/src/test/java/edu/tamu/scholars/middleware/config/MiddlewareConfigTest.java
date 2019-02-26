package edu.tamu.scholars.middleware.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;
import edu.tamu.scholars.middleware.auth.config.PasswordConfig;
import edu.tamu.scholars.middleware.auth.config.TokenConfig;

@ExtendWith(SpringExtension.class)
public class MiddlewareConfigTest {

    @Test
    public void testDefaultConstructor() {
        MiddlewareConfig middlewareConfig = new MiddlewareConfig();
        assertNotNull(middlewareConfig);
        AuthConfig authConfig = middlewareConfig.getAuth();
        assertNotNull(authConfig);
        assertEquals(14, authConfig.getRegistrationTokenDuration());
        PasswordConfig passwordConfig = authConfig.getPassword();
        assertNotNull(passwordConfig);
        assertEquals(180, passwordConfig.getDuration());
        assertEquals(8, passwordConfig.getMinLength());
        assertEquals(64, passwordConfig.getMaxLength());
        TokenConfig tokenConfig = authConfig.getToken();
        assertNotNull(tokenConfig);
        assertEquals(1, tokenConfig.getServerInteger());
        assertEquals("wKFkxTX54UzKx6xCYnC8WlEI2wtOy0PR", tokenConfig.getServerSecret());
        assertEquals(64, tokenConfig.getPseudoRandomNumberBytes());
        MailConfig mailConfig = middlewareConfig.getMail();
        assertNotNull(mailConfig);
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getFrom());
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getReplyTo());
    }

    @Test
    public void testAuthGetterSetter() {
        MiddlewareConfig middlewareConfig = new MiddlewareConfig();
        AuthConfig newAuthConfig = new AuthConfig();
        PasswordConfig passwordConfig = new PasswordConfig();
        passwordConfig.setDuration(90);
        passwordConfig.setMinLength(12);
        passwordConfig.setMaxLength(32);
        newAuthConfig.setPassword(passwordConfig);
        TokenConfig tokenConfig = new TokenConfig();
        tokenConfig.setServerInteger(2);
        tokenConfig.setServerSecret("ulqj2hIqUNbJdTvl1QEeB398XZlhgO3D");
        tokenConfig.setPseudoRandomNumberBytes(128);
        newAuthConfig.setToken(tokenConfig);
        middlewareConfig.setAuth(newAuthConfig);
        AuthConfig authConfig = middlewareConfig.getAuth();
        assertNotNull(authConfig);
        assertEquals(90, authConfig.getPassword().getDuration());
        assertEquals(12, authConfig.getPassword().getMinLength());
        assertEquals(32, authConfig.getPassword().getMaxLength());
        assertEquals(2, authConfig.getToken().getServerInteger());
        assertEquals("ulqj2hIqUNbJdTvl1QEeB398XZlhgO3D", authConfig.getToken().getServerSecret());
        assertEquals(128, authConfig.getToken().getPseudoRandomNumberBytes());
    }

    @Test
    public void testMailGetterSetter() {
        MiddlewareConfig middlewareConfig = new MiddlewareConfig();
        MailConfig newMailConfig = new MailConfig();
        newMailConfig.setFrom("bborring@mailinator.com");
        newMailConfig.setReplyTo("eexciting@mailinator.com");
        middlewareConfig.setMail(newMailConfig);
        MailConfig mailConfig = middlewareConfig.getMail();
        assertNotNull(mailConfig);
        assertEquals("bborring@mailinator.com", mailConfig.getFrom());
        assertEquals("eexciting@mailinator.com", mailConfig.getReplyTo());
    }

    @Test
    public void testHttpGetterSetter() {
        MiddlewareConfig middlewareConfig = new MiddlewareConfig();
        HttpConfig newHttpConfig = new HttpConfig();
        newHttpConfig.setTimeout(120000);
        newHttpConfig.setTimeToLive(90000);
        newHttpConfig.setRequestTimeout(15000);
        newHttpConfig.setSocketTimeout(450000);
        middlewareConfig.setHttp(newHttpConfig);
        HttpConfig httpConfig = middlewareConfig.getHttp();
        assertEquals(120000, httpConfig.getTimeout());
        assertEquals(90000, httpConfig.getTimeToLive());
        assertEquals(15000, httpConfig.getRequestTimeout());
        assertEquals(450000, httpConfig.getSocketTimeout());
    }

}
