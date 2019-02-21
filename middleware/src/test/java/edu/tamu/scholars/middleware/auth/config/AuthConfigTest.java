package edu.tamu.scholars.middleware.auth.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AuthConfigTest {

    @Test
    public void testDefaultConstructor() {
        AuthConfig authConfig = new AuthConfig();
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
    }

    @Test
    public void testAuthGetterSetter() {
        AuthConfig authConfig = new AuthConfig();
        PasswordConfig passwordConfig = new PasswordConfig();
        passwordConfig.setDuration(90);
        passwordConfig.setMinLength(12);
        passwordConfig.setMaxLength(32);
        authConfig.setPassword(passwordConfig);
        assertEquals(90, authConfig.getPassword().getDuration());
        assertEquals(12, authConfig.getPassword().getMinLength());
        assertEquals(32, authConfig.getPassword().getMaxLength());
    }

    @Test
    public void testTokenGetterSetter() {
        AuthConfig authConfig = new AuthConfig();
        TokenConfig tokenConfig = new TokenConfig();
        tokenConfig.setServerInteger(2);
        tokenConfig.setServerSecret("ulqj2hIqUNbJdTvl1QEeB398XZlhgO3D");
        tokenConfig.setPseudoRandomNumberBytes(128);
        authConfig.setToken(tokenConfig);
        assertEquals(2, authConfig.getToken().getServerInteger());
        assertEquals("ulqj2hIqUNbJdTvl1QEeB398XZlhgO3D", authConfig.getToken().getServerSecret());
        assertEquals(128, authConfig.getToken().getPseudoRandomNumberBytes());
    }

    @Test
    public void testRegistrationTokenDurationGetterSetter() {
        AuthConfig authConfig = new AuthConfig();
        authConfig.setRegistrationTokenDuration(3);
        assertEquals(3, authConfig.getRegistrationTokenDuration());
    }

}
