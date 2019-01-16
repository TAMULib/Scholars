package edu.tamu.scholars.middleware.auth.model.repo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.messaging.DeleteEntityMessage;
import edu.tamu.scholars.middleware.messaging.UpdateEntityMessage;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

    private static final String CHANNEL = "/queue/users";

    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @HandleAfterSave
    public void broadcastUserUpdate(User user) {
        simpMessageTemplate.convertAndSend(CHANNEL, new UpdateEntityMessage<User>(user));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), CHANNEL, new UpdateEntityMessage<User>(user));
    }

    @HandleAfterDelete
    public void broadcastUserDelete(User user) {
        simpMessageTemplate.convertAndSend(CHANNEL, new DeleteEntityMessage<String>(user.getEmail()));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), CHANNEL, new DeleteEntityMessage<String>(user.getEmail()));
    }

}
