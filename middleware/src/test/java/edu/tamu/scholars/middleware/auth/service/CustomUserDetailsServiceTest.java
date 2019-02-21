package edu.tamu.scholars.middleware.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomUserDetailsServiceTest extends UserIntegrationTest {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void testLoadUserByUsername() {
        User user = createMockUser();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("bboring@mailinator.com");
        assertNotNull(userDetails);
        assertTrue(bCryptPasswordEncoder.matches("HelloWorld123!", user.getPassword()));
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().toArray(new GrantedAuthority[userDetails.getAuthorities().size()])[0].getAuthority());
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("eexciting@mailinator.com");
        });
    }

}
