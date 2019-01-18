package edu.tamu.scholars.middleware.messaging.handler;

import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

public class CustomStompSubProtocolErrorHandler extends StompSubProtocolErrorHandler {

    @Override
    protected Message<byte[]> handleInternal(StompHeaderAccessor errorHeaderAccessor, byte[] errorPayload, @Nullable Throwable cause, @Nullable StompHeaderAccessor clientHeaderAccessor) {
        errorHeaderAccessor.setDestination(clientHeaderAccessor.getDestination());
        return MessageBuilder.createMessage(errorPayload, errorHeaderAccessor.getMessageHeaders());
    }

}
