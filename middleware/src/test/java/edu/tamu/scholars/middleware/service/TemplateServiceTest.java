package edu.tamu.scholars.middleware.service;

import static edu.tamu.scholars.middleware.auth.RegistrationTestUtility.getMockRegistration;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;

import org.assertj.core.util.Files;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import edu.tamu.scholars.middleware.auth.controller.request.Registration;

@RunWith(SpringRunner.class)
public class TemplateServiceTest {

    @TestConfiguration
    static class TemplateServiceTestContextConfiguration {

        @Bean
        public TemplateService templateService() {
            return new TemplateService();
        }

        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
            templateEngine.setMessageSource(messageSource());
            return templateEngine;
        }

        @Bean
        public SpringResourceTemplateResolver templateResolver() {
            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
            templateResolver.setPrefix("classpath:templates/");
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML5");
            return templateResolver;
        }

        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.addBasenames("i18n/messages");
            return messageSource;
        }

    }

    @Autowired
    private TemplateService templateService;

    @Test
    public void testTemplateConfirmRegistrationMessage() {
        ReflectionTestUtils.setField(templateService, "uiUrl", "http://localhost:4200");
        Registration registration = getMockRegistration("Bob", "Boring", "bboring@mailinator.com");
        String message = templateService.templateConfirmRegistrationMessage(registration, "MTU0NTg1NTI3MDMzMzo3YWFiMWRlYjNkNTRkM2M0NGMxYmI4ODZjNDIyMjEzYzk2NjBkOGIxNDgwMmJjNGY0YjBkYzdiMmE4YWVkMWRmOnsiZmlyc3ROYW1lIjoiQm9iIiwibGFzdE5hbWUiOiJCb3JpbmciLCJlbWFpbCI6ImJib3JpbmdAbWFpbGluYXRvci5jb20ifTpkOTAyZjNlZWZhMDJlYzRiM2M0NDE3ZDFiYjYwMmI5ZTlkYWUxODU1OGUzNGExN2I5NWI1NDdjYjNmODIxZmNmYWZhNDA3ZDI0NmUwOWQ2YTAyMmJmZGI2OGUzODkwNzlhNDRhNjVmZTMyNjlmY2M1M2FlOTUzM2U0ZTE2ZDE4Yg==");
        assertEquals(Files.contentOf(new File("src/test/resources/mock/templates/confirm-registration.html"), Charset.defaultCharset()), message);
    }

}
