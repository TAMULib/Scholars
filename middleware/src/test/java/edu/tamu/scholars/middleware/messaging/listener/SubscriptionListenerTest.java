package edu.tamu.scholars.middleware.messaging.listener;

import static edu.tamu.scholars.middleware.messaging.MessagingTestUtility.getMockStompHeaderAccessor;
import static org.junit.Assert.assertEquals;
import static org.springframework.messaging.simp.stomp.StompCommand.RECEIPT;
import static org.springframework.messaging.simp.stomp.StompCommand.SUBSCRIBE;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.AbstractSubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@RunWith(SpringRunner.class)
public class SubscriptionListenerTest {

    private static Message<?> message;

    @Autowired
    private SubscriptionListener subscriptionListener;

    @TestConfiguration
    static class SubscriptionListenerTestContextConfiguration {

        @Bean
        public SubscriptionListener subscriptionListener() {
            return new SubscriptionListener();
        }

        @Bean
        public AbstractSubscribableChannel clientOutboundChannel() {
            return new AbstractSubscribableChannel() {
                @Override
                protected boolean sendInternal(Message<?> message, long timeout) {
                    SubscriptionListenerTest.message = message;
                    return true;
                }
            };
        }

    }

    @Test
    public void testOnApplicationEvent() {

        StompHeaderAccessor accessor = getMockStompHeaderAccessor(SUBSCRIBE);

        Message<byte[]> message = MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());
        SessionSubscribeEvent event = new SessionSubscribeEvent("This is only a test!", message);

        subscriptionListener.onApplicationEvent(event);

        StompHeaderAccessor receipt = StompHeaderAccessor.wrap(SubscriptionListenerTest.message);

        assertEquals(RECEIPT, receipt.getCommand());
        assertEquals("receipt-0", receipt.getReceipt());
        assertEquals("sub-0", receipt.getSubscriptionId());
        assertEquals("/queue/test", receipt.getDestination());
        assertEquals("jsd8s2l2", receipt.getSessionId());
        assertEquals("bboring@mailinator.com", receipt.getUser().getName());
    }

}
