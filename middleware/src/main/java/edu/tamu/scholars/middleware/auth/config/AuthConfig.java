package edu.tamu.scholars.middleware.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "middleware.auth")
public class AuthConfig {

    private PasswordConfig password = new PasswordConfig();

    private TokenConfig token = new TokenConfig();

    private int registrationTokenDuration = 14;

    public AuthConfig() {

    }

    public PasswordConfig getPassword() {
        return password;
    }

    public void setPassword(PasswordConfig password) {
        this.password = password;
    }

    public TokenConfig getToken() {
        return token;
    }

    public void setToken(TokenConfig token) {
        this.token = token;
    }

    public int getRegistrationTokenDuration() {
        return registrationTokenDuration;
    }

    public void setRegistrationTokenDuration(int registrationTokenDuration) {
        this.registrationTokenDuration = registrationTokenDuration;
    }

}
