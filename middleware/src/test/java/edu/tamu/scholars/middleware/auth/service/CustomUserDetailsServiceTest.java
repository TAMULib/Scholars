package edu.tamu.scholars.middleware.auth.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
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

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        customUserDetailsService.loadUserByUsername("eexciting@mailinator.com");
    }

}
