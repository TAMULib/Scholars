package edu.tamu.scholars.middleware.messaging.handler;

import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.Assert;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

public class CustomStompSubProtocolErrorHandler extends StompSubProtocolErrorHandler {

    private static final byte[] EMPTY_PAYLOAD = new byte[0];

    @Override
    @Nullable
    public Message<byte[]> handleClientMessageProcessingError(@Nullable Message<byte[]> clientMessage, Throwable ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
        accessor.setMessage(ex.getMessage());
        accessor.setLeaveMutable(true);
        StompHeaderAccessor clientHeaderAccessor = null;
        if (clientMessage != null) {
            clientHeaderAccessor = MessageHeaderAccessor.getAccessor(clientMessage, StompHeaderAccessor.class);
            if (clientHeaderAccessor != null) {
                String receiptId = clientHeaderAccessor.getReceipt();
                if (receiptId != null) {
                    accessor.setReceiptId(receiptId);
                }
                String destination = clientHeaderAccessor.getDestination();
                if (destination != null) {
                    accessor.setDestination(destination);
                }
            }
        }
        return handleInternal(accessor, EMPTY_PAYLOAD, ex, clientHeaderAccessor);
    }

    @Override
    @Nullable
    public Message<byte[]> handleErrorMessageToClient(Message<byte[]> errorMessage) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(errorMessage, StompHeaderAccessor.class);
        Assert.notNull(accessor, "No StompHeaderAccessor");
        if (!accessor.isMutable()) {
            accessor = StompHeaderAccessor.wrap(errorMessage);
        }
        System.out.println("\n\n\n" + accessor + "\n\n\n");
        return handleInternal(accessor, errorMessage.getPayload(), null, null);
    }

    protected Message<byte[]> handleInternal(StompHeaderAccessor errorHeaderAccessor, byte[] errorPayload, @Nullable Throwable cause, @Nullable StompHeaderAccessor clientHeaderAccessor) {

        return MessageBuilder.createMessage(errorPayload, errorHeaderAccessor.getMessageHeaders());
    }

}
