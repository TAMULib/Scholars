package edu.tamu.scholars.middleware.auth.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.config.SolrTestConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SolrTestConfig.class)
// @formatter:off
@SpringBootTest(properties = {
    "spring.profiles.active=default",
    "spring.h2.console.enabled=true",
    "spring.data.solr.host=",
    "spring.data.solr.repositories.enabled=false"
})
// @formatter:on
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
