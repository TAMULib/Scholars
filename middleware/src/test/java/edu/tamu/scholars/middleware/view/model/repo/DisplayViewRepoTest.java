package edu.tamu.scholars.middleware.view.model.repo;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDisplayView;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.view.model.DisplayView;

public class DisplayViewRepoTest extends ViewRepoTest<DisplayView, DisplayViewRepo> {

    @TestConfiguration
    static class DisplayViewRepoTestContextConfiguration {

        @Bean
        public MiddlewareConfig middlewareConfig() {
            return new MiddlewareConfig();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }

    @Override
    protected DisplayView getMockView() {
        return getMockDisplayView();
    }

}
