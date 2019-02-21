package edu.tamu.scholars.middleware.auth.service;

import static edu.tamu.scholars.middleware.auth.RegistrationTestUtility.getMockRegistration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.assertj.core.util.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.auth.RegistrationIntegrationTest;
import edu.tamu.scholars.middleware.auth.config.AuthConfig;
import edu.tamu.scholars.middleware.auth.config.TokenConfig;
import edu.tamu.scholars.middleware.auth.controller.exception.RegistrationException;
import edu.tamu.scholars.middleware.auth.controller.request.Registration;
import edu.tamu.scholars.middleware.auth.model.Role;
import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.service.EmailService;
import edu.tamu.scholars.middleware.service.TemplateService;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RegistrationServiceTest extends RegistrationIntegrationTest {

    @TestConfiguration
    static class RegistrationServiceTestContextConfiguration {

        @Bean
        public MiddlewareConfig middlewareConfig() {
            return new MiddlewareConfig();
        }

        @Bean
        public AuthConfig authConfig() {
            return new AuthConfig();
        }

        @Bean
        public RegistrationService registrationService() {
            return new RegistrationService();
        }

        @Bean
        public TemplateService templateService() {
            return new TemplateService();
        }

        @Bean
        public EmailService emailService() {
            return new EmailService();
        }

        @Bean
        public TokenService tokenService() throws NoSuchAlgorithmException {
            TokenConfig tokenConfig = new TokenConfig();
            KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
            tokenService.setServerInteger(tokenConfig.getServerInteger());
            tokenService.setServerSecret(tokenConfig.getServerSecret());
            tokenService.setPseudoRandomNumberBytes(tokenConfig.getPseudoRandomNumberBytes());
            tokenService.setSecureRandom(SecureRandom.getInstanceStrong());
            return tokenService;
        }

        @Bean
        public MessageSource messageSource() {
            return new ResourceBundleMessageSource();
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SimpMessagingTemplate simpMessageTemplate() {
            return new SimpMessagingTemplate(new MessageChannel() {
                @Override
                public boolean send(Message<?> message, long timeout) {
                    return true;
                }
            });
        }

    }

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private TemplateService templateService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private MessageSource messageSource;

    @Before
    public void setup() {
        doReturn(Files.contentOf(new File("src/test/resources/mock/templates/email/confirm-registration.html"), Charset.defaultCharset())).when(templateService).templateConfirmRegistrationMessage(any(Registration.class), any(String.class));
        doNothing().when(emailService).send(any(String.class), any(String.class), any(String.class));
        doReturn("VIVO Scholars Discovery Confirm Registration").when(messageSource).getMessage("RegistrationService.confirmationEmailSubject", new Object[0], LocaleContextHolder.getLocale());
        doReturn("Success").when(messageSource).getMessage("RegistrationService.submitSuccess", new Object[0], LocaleContextHolder.getLocale());
        doReturn("Unable to complete registration. Email eexciting@mailinator.com not found.").when(messageSource).getMessage("RegistrationService.unableToCompleteEmailNotFound", new Object[0], LocaleContextHolder.getLocale());
        doReturn("Token has expired.").when(messageSource).getMessage("RegistrationService.tokenExpired", new Object[0], LocaleContextHolder.getLocale());
        doReturn("Email bboring@mailinator.com has already been confirmed.").when(messageSource).getMessage("RegistrationService.emailAlreadyConfirmed", new Object[0], LocaleContextHolder.getLocale());
        doReturn("Unable to confirm registration. Email eexciting@mailinator.com not found.").when(messageSource).getMessage("RegistrationService.unableToConfirmEmailNotFound", new Object[0], LocaleContextHolder.getLocale());
    }

    @Test
    public void testCreateFirstThreeUsers() throws RegistrationException, IOException {
        createUser("Bob", "Boring", "bboring@mailinator.com", "HelloWorld123~");
        createUser("Eddie", "Exciting", "eexciting@mailinator.com", "HelloWorld123!");
        createUser("Carl", "Calamitous", "ccalamitous@mailinator.com", "HelloWorld123@");
    }

    @Test
    public void testSubmit() throws JsonProcessingException {
        Registration registration = getMockRegistration("Bob", "Boring", "bboring@mailinator.com");
        registration = registrationService.submit(registration);
        assertEquals("bboring@mailinator.com", registration.getEmail());
        assertEquals("Bob", registration.getFirstName());
        assertEquals("Boring", registration.getLastName());
    }

    @Test
    public void testConfirm() throws JsonParseException, JsonMappingException, IOException, RegistrationException {
        testSubmit();
        Token token = getMockToken("Bob", "Boring", "bboring@mailinator.com");
        Registration registration = registrationService.confirm(token.getKey());
        assertEquals("bboring@mailinator.com", registration.getEmail());
        assertEquals("Bob", registration.getFirstName());
        assertEquals("Boring", registration.getLastName());
    }

    @Test(expected = RegistrationException.class)
    public void testConfirmEmailNotFound() throws JsonParseException, JsonMappingException, IOException, RegistrationException {
        Token token = getMockToken("Bob", "Boring", "bboring@mailinator.com");
        registrationService.confirm(token.getKey());
    }

    @Test(expected = RegistrationException.class)
    public void testConfirmTokenExpired() throws JsonParseException, JsonMappingException, IOException, RegistrationException {
        testSubmit();
        authConfig.setRegistrationTokenDuration(0);
        Token token = getMockToken("Bob", "Boring", "bboring@mailinator.com");
        registrationService.confirm(token.getKey());
    }

    @Test
    public void testComplete() throws IOException, RegistrationException {
        testConfirm();
        Registration registration = getMockRegistration("Bob", "Boring", "bboring@mailinator.com");
        registration.setPassword("HelloWorld123!");
        registration.setConfirm("HelloWorld123!");
        User user = registrationService.complete(registration);
        assertEquals("bboring@mailinator.com", user.getEmail());
        assertEquals("Bob", user.getFirstName());
        assertEquals("Boring", user.getLastName());
        assertTrue(bCryptPasswordEncoder.matches("HelloWorld123!", user.getPassword()));
        assertEquals(0, user.getOldPasswords().size());
        assertEquals(Role.ROLE_SUPER_ADMIN, user.getRole());
        assertTrue(user.isConfirmed());
        assertTrue(user.isActive());
        assertTrue(user.isEnabled());
    }

    @Test(expected = RegistrationException.class)
    public void testCompleteWithoutSubmitAndConfirm() throws IOException, RegistrationException {
        Registration registration = getMockRegistration("Bob", "Boring", "bboring@mailinator.com");
        registration.setPassword("HelloWorld123!");
        registration.setConfirm("HelloWorld123!");
        registrationService.complete(registration);
    }

    @Test(expected = RegistrationException.class)
    public void testCompleteEmailAlreadyConfirmed() throws JsonParseException, JsonMappingException, IOException, RegistrationException {
        testComplete();
        Registration registration = getMockRegistration("Bob", "Boring", "bboring@mailinator.com");
        registration.setPassword("HelloWorld123!");
        registration.setConfirm("HelloWorld123!");
        registrationService.complete(registration);
    }

    @After
    public void cleanup() {
        authConfig.setRegistrationTokenDuration(14);
    }

    private void createUser(String firstName, String lastName, String email, String password) throws IOException, RegistrationException {
        Registration registration = getMockRegistration(firstName, lastName, email);
        registration = registrationService.submit(registration);
        assertEquals(email, registration.getEmail());
        assertEquals(firstName, registration.getFirstName());
        assertEquals(lastName, registration.getLastName());

        Token token = getMockToken(firstName, lastName, email);
        registration = registrationService.confirm(token.getKey());
        assertEquals(email, registration.getEmail());
        assertEquals(firstName, registration.getFirstName());
        assertEquals(lastName, registration.getLastName());

        registration.setPassword(password);
        registration.setConfirm(password);

        User user = registrationService.complete(registration);
        assertEquals(email, user.getEmail());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertTrue(bCryptPasswordEncoder.matches(password, user.getPassword()));
        assertEquals(0, user.getOldPasswords().size());

        if (userRepo.count() == 1) {
            assertEquals(Role.ROLE_SUPER_ADMIN, user.getRole());
        } else if (userRepo.count() == 2) {
            assertEquals(Role.ROLE_ADMIN, user.getRole());
        } else {
            assertEquals(Role.ROLE_USER, user.getRole());
        }

        assertTrue(user.isConfirmed());
        assertTrue(user.isActive());
        assertTrue(user.isEnabled());
    }

}
