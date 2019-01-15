package edu.tamu.scholars.middleware.auth.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.messaging.EntityMessage;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @Autowired
    protected SimpMessagingTemplate simpMessageTemplate;

    @HandleAfterCreate
    public void broadcastUserCreate(User user) {
        simpMessageTemplate.convertAndSend(channel(), EntityMessage.create(user));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), channel(), EntityMessage.create(user));
    }

    @HandleAfterSave
    public void broadcastUserUpdate(User user) {
        simpMessageTemplate.convertAndSend(channel(), EntityMessage.update(user));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), channel(), EntityMessage.update(user));
    }

    @HandleAfterDelete
    public void broadcastUserDelete(User user) {
        simpMessageTemplate.convertAndSend(channel(), EntityMessage.delete(user));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), channel(), EntityMessage.delete(user));
    }

    private String channel() {
        return "/queue/users";
    }

}
