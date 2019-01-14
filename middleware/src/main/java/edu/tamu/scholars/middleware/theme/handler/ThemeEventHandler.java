package edu.tamu.scholars.middleware.theme.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.transaction.annotation.Transactional;

import edu.tamu.scholars.middleware.theme.exception.CreateActiveThemeException;
import edu.tamu.scholars.middleware.theme.exception.DeleteActiveThemeException;
import edu.tamu.scholars.middleware.theme.model.Theme;
import edu.tamu.scholars.middleware.theme.model.repo.ThemeRepo;

@RepositoryEventHandler(Theme.class)
public class ThemeEventHandler {

    @Autowired
    private ThemeRepo themeRepo;

    @Transactional
    @HandleBeforeSave
    public void handleThemeSave(Theme theme) {
        if (theme.isActive()) {
            themeRepo.deactivate();
        }
    }

    @HandleBeforeCreate
    public void handleThemeCreate(Theme theme) throws CreateActiveThemeException {
        if (theme.isActive()) {
            throw new CreateActiveThemeException("You cannot create an active theme!");
        }
    }

    @HandleBeforeDelete
    public void handleThemeDelete(Theme theme) throws DeleteActiveThemeException {
        if (theme.isActive()) {
            throw new DeleteActiveThemeException("You cannot delete the active theme!");
        }
    }

}
