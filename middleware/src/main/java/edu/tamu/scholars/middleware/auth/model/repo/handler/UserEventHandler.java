package edu.tamu.scholars.middleware.auth.model.repo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.messaging.CreateEntityMessage;
import edu.tamu.scholars.middleware.messaging.DeleteEntityMessage;
import edu.tamu.scholars.middleware.messaging.UpdateEntityMessage;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @HandleAfterCreate
    public void broadcastUserCreate(User user) {
        simpMessageTemplate.convertAndSend(channel(), new CreateEntityMessage<User>(user));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), channel(), new CreateEntityMessage<User>(user));
    }

    @HandleAfterSave
    public void broadcastUserUpdate(User user) {
        simpMessageTemplate.convertAndSend(channel(), new UpdateEntityMessage<User>(user));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), channel(), new UpdateEntityMessage<User>(user));
    }

    @HandleAfterDelete
    public void broadcastUserDelete(User user) {
        simpMessageTemplate.convertAndSend(channel(), new DeleteEntityMessage<String>(user.getEmail()));
        simpMessageTemplate.convertAndSendToUser(user.getEmail(), channel(), new DeleteEntityMessage<String>(user.getEmail()));
    }

    private String channel() {
        return "/queue/users";
    }

}
