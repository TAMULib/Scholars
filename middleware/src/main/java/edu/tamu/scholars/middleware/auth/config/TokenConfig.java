package edu.tamu.scholars.middleware.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "middleware.auth.token")
public class TokenConfig {

    private int serverInteger = 1;

    private String serverSecret = "wKFkxTX54UzKx6xCYnC8WlEI2wtOy0PR";

    private int pseudoRandomNumberBytes = 64;

    public TokenConfig() {

    }

    public int getServerInteger() {
        return serverInteger;
    }

    public void setServerInteger(int serverInteger) {
        this.serverInteger = serverInteger;
    }

    public String getServerSecret() {
        return serverSecret;
    }

    public void setServerSecret(String serverSecret) {
        this.serverSecret = serverSecret;
    }

    public int getPseudoRandomNumberBytes() {
        return pseudoRandomNumberBytes;
    }

    public void setPseudoRandomNumberBytes(int pseudoRandomNumberBytes) {
        this.pseudoRandomNumberBytes = pseudoRandomNumberBytes;
    }

}
