package edu.tamu.scholars.middleware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.tamu.scholars.middleware.auth.handler.UserEventHandler;
import edu.tamu.scholars.middleware.theme.handler.ThemeEventHandler;

@Configuration
public class RepositoryConfig {

    @Bean
    public ThemeEventHandler themeEventHandler() {
        return new ThemeEventHandler();
    }

    @Bean
    public UserEventHandler userEventHandler() {
        return new UserEventHandler();
    }

}
