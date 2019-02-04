package edu.tamu.scholars.middleware.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
        resolver.setApplicationContext(applicationContext);
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
        resolver.setApplicationContext(applicationContext);
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
