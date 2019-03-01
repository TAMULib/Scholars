package edu.tamu.scholars.middleware.view.model.repo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import edu.tamu.scholars.middleware.messaging.CreateEntityMessage;
import edu.tamu.scholars.middleware.messaging.DeleteEntityMessage;
import edu.tamu.scholars.middleware.messaging.UpdateEntityMessage;
import edu.tamu.scholars.middleware.view.model.View;

public abstract class ViewEventHandler<V extends View> {

    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @HandleAfterCreate
    public void broadcastThemeCreate(V view) {
        simpMessageTemplate.convertAndSend(getChannel(), new CreateEntityMessage<V>(view));
    }

    @HandleAfterSave
    public void broadcastThemeUpdate(V view) {
        simpMessageTemplate.convertAndSend(getChannel(), new UpdateEntityMessage<V>(view));
    }

    @HandleAfterDelete
    public void broadcastThemeDelete(V view) {
        simpMessageTemplate.convertAndSend(getChannel(), new DeleteEntityMessage<String>(view.getIdentifier()));
    }

    protected abstract String getChannel();

}
