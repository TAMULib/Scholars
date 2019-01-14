package edu.tamu.scholars.middleware.auth.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.profiles.active=default", "spring.h2.console.enabled=true" })
public class WebSecurityConfigTest {

    @Value("${spring.profiles.active:default}")
    private String profile;

    @Value("${spring.h2.console.enabled:true}")
    private boolean h2ConsoleEnabled;

    @Test
    public void testDefaultProfile() {
        assertEquals("default", profile);
    }

    @Test
    public void testH2ConsoleEnabled() {
        assertTrue(h2ConsoleEnabled);
    }

}
