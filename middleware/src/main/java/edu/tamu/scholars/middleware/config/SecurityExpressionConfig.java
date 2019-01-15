package edu.tamu.scholars.middleware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.messaging.access.expression.DefaultMessageSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
public class SecurityExpressionConfig {

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(buildRoleHierarchy());
        return roleHierarchy;
    }

    @Bean
    public SecurityExpressionHandler<FilterInvocation> securityExpressionHandler() {
        DefaultWebSecurityExpressionHandler securityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        securityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return securityExpressionHandler;
    }

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public SecurityExpressionHandler<Message<Object>> messageSecurityExpressionHandler() {
        DefaultMessageSecurityExpressionHandler securityExpressionHandler = new DefaultMessageSecurityExpressionHandler<Message<Object>>();
        securityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return securityExpressionHandler;
    }

    private String buildRoleHierarchy() {
        return "ROLE_SUPER_ADMIN > ROLE_ADMIN > ROLE_USER";
    }

}
