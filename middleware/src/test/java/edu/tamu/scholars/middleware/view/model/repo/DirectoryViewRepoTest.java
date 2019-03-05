package edu.tamu.scholars.middleware.view.model.repo;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDirectoryView;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.view.model.DirectoryView;

public class DirectoryViewRepoTest extends CollectionViewRepoTest<DirectoryView, DirectoryViewRepo> {

    @TestConfiguration
    static class DirectoryViewRepoTestContextConfiguration {

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
    protected DirectoryView getMockView() {
        return getMockDirectoryView();
    }

}
