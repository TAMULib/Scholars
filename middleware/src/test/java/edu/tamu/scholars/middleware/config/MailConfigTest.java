package edu.tamu.scholars.middleware.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MailConfigTest {

    @Test
    public void testDefaultConstructor() {
        MailConfig mailConfig = new MailConfig();
        assertNotNull(mailConfig);
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getFrom());
        assertEquals("scholarsdiscovery@gmail.com", mailConfig.getReplyTo());
    }

    @Test
    public void testFromGetterSetter() {
        MailConfig mailConfig = new MailConfig();
        mailConfig.setFrom("bborring@mailinator.com");
        assertEquals("bborring@mailinator.com", mailConfig.getFrom());
    }

    @Test
    public void testToGetterSetter() {
        MailConfig mailConfig = new MailConfig();
        mailConfig.setReplyTo("eexciting@mailinator.com");
        assertEquals("eexciting@mailinator.com", mailConfig.getReplyTo());
    }

}
