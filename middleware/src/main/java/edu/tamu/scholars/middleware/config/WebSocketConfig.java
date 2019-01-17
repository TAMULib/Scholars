package edu.tamu.scholars.middleware.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

    @Value("${ui.url:http://localhost:4200}")
    private String uiUrl;

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry registry) {
        // @formatter:off
        registry
            .addEndpoint("/connect")
            .setAllowedOrigins(uiUrl).withSockJS()
            .setInterceptors(new HttpSessionHandshakeInterceptor());
        // @formatter:on
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
        registry.setApplicationDestinationPrefixes("/ws");
    }

}
