package edu.tamu.scholars.middleware.config;

import static org.springframework.messaging.simp.SimpMessageType.CONNECT;
import static org.springframework.messaging.simp.SimpMessageType.DISCONNECT;
import static org.springframework.messaging.simp.SimpMessageType.MESSAGE;
import static org.springframework.messaging.simp.SimpMessageType.SUBSCRIBE;
import static org.springframework.messaging.simp.SimpMessageType.UNSUBSCRIBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Autowired
    private SecurityExpressionHandler<Message<Object>> messageSecurityExpressionHandler;

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        // @formatter:off
        messages
            .expressionHandler(messageSecurityExpressionHandler)

            .simpTypeMatchers(CONNECT, UNSUBSCRIBE, DISCONNECT)
                .permitAll()
            .simpSubscribeDestMatchers("/queue/public", "/queue/themes")
                .permitAll()
            .simpSubscribeDestMatchers("/user/queue/users")
                .hasRole("USER")
            .simpSubscribeDestMatchers("/queue/users")
                .hasRole("ADMIN")
            .simpTypeMatchers(SUBSCRIBE, MESSAGE)
                .denyAll()
            .anyMessage()
                .denyAll();
        // @formatter:off
    }
    
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

}