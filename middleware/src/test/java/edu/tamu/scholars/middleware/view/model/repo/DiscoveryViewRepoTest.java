package edu.tamu.scholars.middleware.view.model.repo;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDiscoveryView;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;

public class DiscoveryViewRepoTest extends CollectionViewRepoTest<DiscoveryView, DiscoveryViewRepo> {

    @TestConfiguration
    static class DiscoveryViewRepoTestContextConfiguration {

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
    protected DiscoveryView getMockView() {
        return getMockDiscoveryView();
    }

}
