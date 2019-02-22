package edu.tamu.scholars.middleware.messaging.handler;

import static edu.tamu.scholars.middleware.messaging.MessagingTestUtility.getMockStompHeaderAccessor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.messaging.simp.stomp.StompCommand.ERROR;
import static org.springframework.messaging.simp.stomp.StompCommand.SUBSCRIBE;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CustomStompSubProtocolErrorHandlerTest {

    private CustomStompSubProtocolErrorHandler customStompSubProtocolErrorHandler = new CustomStompSubProtocolErrorHandler();

    @Test
    public void testHandleInternal() {
        String errorMessage = "Access denied!";
        StompHeaderAccessor clientHeaderAccessor = getMockStompHeaderAccessor(SUBSCRIBE);
        StompHeaderAccessor errorHeaderAccessor = getMockStompHeaderAccessor(ERROR);

        Message<byte[]> message = customStompSubProtocolErrorHandler.handleInternal(errorHeaderAccessor, errorMessage.getBytes(), new AccessDeniedException(errorMessage), clientHeaderAccessor);

        StompHeaderAccessor error = StompHeaderAccessor.wrap(message);

        assertEquals(ERROR, error.getCommand());
        assertEquals("receipt-0", error.getReceipt());
        assertEquals("sub-0", error.getSubscriptionId());
        assertEquals("/queue/test", error.getDestination());
        assertEquals("jsd8s2l2", error.getSessionId());
        assertEquals("bboring@mailinator.com", error.getUser().getName());
        assertEquals(errorMessage, new String(message.getPayload()));
    }

}
