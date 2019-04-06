package edu.tamu.scholars.middleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;

@Component
@ConfigurationProperties(prefix = "middleware")
public class MiddlewareConfig {

    private boolean loadDefaults = true;

    private boolean updateDefaults = false;

    private AuthConfig auth = new AuthConfig();

    private MailConfig mail = new MailConfig();

    private HttpConfig http = new HttpConfig();

    public MiddlewareConfig() {

    }

    public boolean isLoadDefaults() {
        return loadDefaults;
    }

    public void setLoadDefaults(boolean loadDefaults) {
        this.loadDefaults = loadDefaults;
    }

    public boolean isUpdateDefaults() {
        return updateDefaults;
    }

    public void setUpdateDefaults(boolean updateDefaults) {
        this.updateDefaults = updateDefaults;
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

    public HttpConfig getHttp() {
        return http;
    }

    public void setHttp(HttpConfig http) {
        this.http = http;
    }

}
