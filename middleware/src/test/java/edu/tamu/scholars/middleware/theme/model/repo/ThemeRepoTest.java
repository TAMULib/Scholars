package edu.tamu.scholars.middleware.theme.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.theme.ThemeIntegrationTest;
import edu.tamu.scholars.middleware.theme.model.Theme;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ThemeRepoTest extends ThemeIntegrationTest {

    @TestConfiguration
    static class ThemeRepoTestContextConfiguration {

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
    public void testCreate() {
        assertEquals(0, themeRepo.count());
        Theme theme = getMockTheme();
        themeRepo.save(theme);
        assertEquals(1, themeRepo.count());
    }

    @Test
    public void testRead() {
        testCreate();
        Optional<Theme> theme = themeRepo.findByName("Test");
        assertTrue(theme.isPresent());
    }

    @Test
    public void testUpdate() {
        testCreate();
        Optional<Theme> theme = themeRepo.findByName("Test");
        theme.get().setActive(true);

        themeRepo.save(theme.get());

        assertEquals(1, themeRepo.count());

        theme = themeRepo.findByName("Test");
        assertEquals("Test", theme.get().getName());
        assertTrue(theme.get().isActive());
    }

    @Test
    public void testDelete() {
        testCreate();
        assertEquals(1, themeRepo.count());
        Optional<Theme> theme = themeRepo.findByName("Test");
        themeRepo.delete(theme.get());
        assertEquals(0, themeRepo.count());
    }

}
