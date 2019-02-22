package edu.tamu.scholars.middleware.messaging;

import java.security.Principal;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class MessagingTestUtility {

    public static StompHeaderAccessor getMockStompHeaderAccessor(StompCommand command) {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(command);
        accessor.setReceipt("receipt-0");
        accessor.setSubscriptionId("sub-0");
        accessor.setDestination("/queue/test");
        accessor.setSessionId("jsd8s2l2");
        accessor.setUser(new Principal() {
            @Override
            public String getName() {
                return "bboring@mailinator.com";
            }
        });
        return accessor;
    }

}
