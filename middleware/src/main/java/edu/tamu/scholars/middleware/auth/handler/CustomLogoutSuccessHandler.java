package edu.tamu.scholars.middleware.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private MessageSource messageSource;

    public CustomLogoutSuccessHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        response.getWriter().write(messageSource.getMessage("CustomLogoutSuccessHandler.success", new Object[0], LocaleContextHolder.getLocale()));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
