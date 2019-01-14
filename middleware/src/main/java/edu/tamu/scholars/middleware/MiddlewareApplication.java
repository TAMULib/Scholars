package edu.tamu.scholars.middleware;

import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_DURATION_IN_DAYS;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MAX_LENGTH;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MIN_LENGTH;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;
import edu.tamu.scholars.middleware.auth.config.PasswordConfig;
import edu.tamu.scholars.middleware.config.MiddlewareConfig;

@SpringBootApplication
public class MiddlewareApplication {

    @Autowired
    private MiddlewareConfig middlewareConfig;

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareApplication.class, args);
    }

    @PostConstruct
    private void initializePropertyConstants() {
        AuthConfig auth = middlewareConfig.getAuth();
        PasswordConfig password = auth.getPassword();
        PASSWORD_DURATION_IN_DAYS = password.getDuration();
        PASSWORD_MIN_LENGTH = password.getMinLength();
        PASSWORD_MAX_LENGTH = password.getMaxLength();
    }

}
