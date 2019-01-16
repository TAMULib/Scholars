package edu.tamu.scholars.middleware.theme.model.repo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;

import edu.tamu.scholars.middleware.messaging.CreateEntityMessage;
import edu.tamu.scholars.middleware.messaging.DeleteEntityMessage;
import edu.tamu.scholars.middleware.messaging.UpdateEntityMessage;
import edu.tamu.scholars.middleware.theme.exception.CreateActiveThemeException;
import edu.tamu.scholars.middleware.theme.exception.DeleteActiveThemeException;
import edu.tamu.scholars.middleware.theme.model.Theme;
import edu.tamu.scholars.middleware.theme.model.repo.ThemeRepo;

@RepositoryEventHandler(Theme.class)
public class ThemeEventHandler {

    @Autowired
    private ThemeRepo themeRepo;

    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @HandleBeforeCreate
    public void handleBeforeThemeCreate(Theme theme) throws CreateActiveThemeException {
        if (theme.isActive()) {
            throw new CreateActiveThemeException("You cannot create an active theme!");
        }
    }

    @Transactional
    @HandleBeforeSave
    public void handleBeforeThemeSave(Theme theme) {
        if (theme.isActive()) {
            themeRepo.deactivate();
        }
    }

    @HandleBeforeDelete
    public void handleBeforeThemeDelete(Theme theme) throws DeleteActiveThemeException {
        if (theme.isActive()) {
            throw new DeleteActiveThemeException("You cannot delete the active theme!");
        }
    }

    @HandleAfterCreate
    public void broadcastThemeCreate(Theme theme) {
        simpMessageTemplate.convertAndSend(channel(), new CreateEntityMessage<Theme>(theme));
    }

    @HandleAfterSave
    public void broadcastThemeUpdate(Theme theme) {
        simpMessageTemplate.convertAndSend(channel(), new UpdateEntityMessage<Theme>(theme));
    }

    @HandleAfterDelete
    public void broadcastThemeDelete(Theme theme) {
        simpMessageTemplate.convertAndSend(channel(), new DeleteEntityMessage<String>(theme.getName()));
    }

    private String channel() {
        return "/queue/themes";
    }

}
