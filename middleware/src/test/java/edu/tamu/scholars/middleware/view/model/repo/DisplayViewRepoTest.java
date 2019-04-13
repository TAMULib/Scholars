package edu.tamu.scholars.middleware.view.model.repo;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDisplayView;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
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

    @Test
    public void testRead() {
        testCreate();
        List<String> types = new ArrayList<String>();
        types.add("FacultyMember");
        Optional<DisplayView> view = viewRepo.findByTypesIn(types);
        assertTrue(view.isPresent());
    }

    @Override
    protected DisplayView getMockView() {
        return getMockDisplayView();
    }

}
