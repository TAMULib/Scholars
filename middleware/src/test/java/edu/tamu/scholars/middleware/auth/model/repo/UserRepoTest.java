package edu.tamu.scholars.middleware.auth.model.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.Role;
import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.config.MiddlewareConfig;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepoTest extends UserIntegrationTest {

    @TestConfiguration
    static class UserRepoTestContextConfiguration {

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
        assertEquals(0, userRepo.count());
        createMockUser();
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testRead() {
        testCreate();
        Optional<User> user = userRepo.findByEmail("bboring@mailinator.com");
        assertTrue(user.isPresent());
        assertEquals("bboring@mailinator.com", user.get().getEmail());
        assertEquals("Bob", user.get().getFirstName());
        assertEquals("Boring", user.get().getLastName());
        assertTrue(bCryptPasswordEncoder.matches("HelloWorld123!", user.get().getPassword()));
        assertEquals(0, user.get().getOldPasswords().size());
        assertEquals(Role.ROLE_USER, user.get().getRole());
        assertTrue(user.get().isConfirmed());
        assertTrue(user.get().isActive());
        assertTrue(user.get().isEnabled());
    }

    @Test
    public void testUpdate() {
        testCreate();
        Optional<User> user = userRepo.findByEmail("bboring@mailinator.com");
        user.get().setFirstName("Robert");
        user.get().setRole(Role.ROLE_ADMIN);
        user.get().setOldPasswords(new ArrayList<String>(Arrays.asList(new String[] { user.get().getPassword() })));
        user.get().setPassword(bCryptPasswordEncoder.encode("HelloWorld123~"));
        user.get().setActive(false);
        user.get().setEnabled(false);

        userRepo.save(user.get());

        assertEquals(1, userRepo.count());

        user = userRepo.findByEmail("bboring@mailinator.com");
        assertEquals("Robert", user.get().getFirstName());
        assertEquals("bboring@mailinator.com", user.get().getEmail());
        assertTrue(bCryptPasswordEncoder.matches("HelloWorld123~", user.get().getPassword()));
        assertEquals(1, user.get().getOldPasswords().size());
        assertTrue(bCryptPasswordEncoder.matches("HelloWorld123!", user.get().getOldPasswords().get(0)));
        assertEquals(Role.ROLE_ADMIN, user.get().getRole());
        assertTrue(user.get().isConfirmed());
        assertFalse(user.get().isActive());
        assertFalse(user.get().isEnabled());
    }

    @Test
    public void testDelete() {
        testCreate();
        assertEquals(1, userRepo.count());
        Optional<User> user = userRepo.findByEmail("bboring@mailinator.com");
        userRepo.delete(user.get());
        assertEquals(0, userRepo.count());
    }

}
