package edu.tamu.scholars.middleware.config;

import static org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import edu.tamu.scholars.middleware.messaging.handler.CustomStompSubProtocolErrorHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

    private static final String SPRING_SESSION_ID_ATTR_NAME = "SPRING.SESSION.ID";

    @Value("${ui.url:http://localhost:4200}")
    private String uiUrl;

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry registry) {
        // @formatter:off
        registry.setErrorHandler(new CustomStompSubProtocolErrorHandler());
        registry
            .addEndpoint("/connect")
            .setAllowedOrigins(uiUrl).withSockJS()
            .setInterceptors(
                new HttpSessionHandshakeInterceptor(),
                // TODO: remove when patched in spring-session, https://github.com/spring-projects/spring-session/issues/561
                new HandshakeInterceptor() {

                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                        if (attributes.containsKey(HTTP_SESSION_ID_ATTR_NAME)) {
                            attributes.put(SPRING_SESSION_ID_ATTR_NAME, attributes.get(HTTP_SESSION_ID_ATTR_NAME));
                        }
                        return true;
                    }

                    @Override
                    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

                    }

                }
            );
        // @formatter:on
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
        registry.setApplicationDestinationPrefixes("/ws");
    }

}
