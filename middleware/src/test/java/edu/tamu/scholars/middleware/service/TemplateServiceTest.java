package edu.tamu.scholars.middleware.service;

import static edu.tamu.scholars.middleware.auth.RegistrationTestUtility.getMockRegistration;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.charset.Charset;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import edu.tamu.scholars.middleware.auth.controller.request.Registration;

@ExtendWith(SpringExtension.class)
public class TemplateServiceTest {

    @TestConfiguration
    static class TemplateServiceTestContextConfiguration {

        @Bean
        public TemplateService templateService() {
            return new TemplateService();
        }

        @Bean
        public SpringTemplateEngine templateEngine() {
            final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.addTemplateResolver(htmlTemplateResolver());
            templateEngine.addTemplateResolver(sparqlTemplateResolver());
            templateEngine.setMessageSource(messageSource());
            return templateEngine;
        }

        @Bean
        public SpringResourceTemplateResolver htmlTemplateResolver() {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setOrder(Integer.valueOf(1));
            resolver.setPrefix("classpath:/templates/emails/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding(UTF_8.name());
            resolver.setCheckExistence(true);
            resolver.setCacheable(false);
            return resolver;
        }

        @Bean
        public SpringResourceTemplateResolver sparqlTemplateResolver() {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setOrder(Integer.valueOf(2));
            resolver.setPrefix("classpath:/templates/sparql/");
            resolver.setSuffix(".sparql");
            resolver.setTemplateMode(TemplateMode.TEXT);
            resolver.setCharacterEncoding(UTF_8.name());
            resolver.setCheckExistence(true);
            resolver.setCacheable(false);
            return resolver;
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
        assertEquals(Files.contentOf(new File("src/test/resources/mock/templates/email/confirm-registration.html"), Charset.defaultCharset()), message);
    }

    @Test
    public void testTemplateSparql() {
        String query = templateService.templateSparql("collection", "http://xmlns.com/foaf/0.1/Person");
        assertEquals(Files.contentOf(new File("src/test/resources/mock/templates/sparql/collection.sparql"), Charset.defaultCharset()), query);
    }

}
