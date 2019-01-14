package edu.tamu.scholars.middleware.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import edu.tamu.scholars.middleware.auth.controller.request.Registration;

@Service
public class TemplateService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${ui.url}")
    private String uiUrl;

    public String templateConfirmRegistrationMessage(Registration registration, String key) {
        Context ctx = new Context(Locale.getDefault());
        ctx.setVariable("registration", registration);
        ctx.setVariable("link", uiUrl + "?key=" + key);
        return templateEngine.process("confirm-registration", ctx);
    }

}
