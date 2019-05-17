package edu.tamu.scholars.middleware.view.model.repo.handler;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import edu.tamu.scholars.middleware.view.model.DirectoryView;

@RepositoryEventHandler(DirectoryView.class)
public class DirectoryViewEventHandler extends ViewEventHandler<DirectoryView> {

    public static final String DIRECTORY_VIEWS_CHANNEL = "/queue/directory-views";

    @Override
    protected String getChannel() {
        return DIRECTORY_VIEWS_CHANNEL;
    }

}
