package edu.tamu.scholars.middleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;

@Component
@ConfigurationProperties(prefix = "middleware")
public class MiddlewareConfig {

    private AuthConfig auth = new AuthConfig();

    private MailConfig mail = new MailConfig();

    public MiddlewareConfig() {

    }

    public AuthConfig getAuth() {
        return auth;
    }

    public void setAuth(AuthConfig auth) {
        this.auth = auth;
    }

    public MailConfig getMail() {
        return mail;
    }

    public void setMail(MailConfig mail) {
        this.mail = mail;
    }

}
