package edu.tamu.scholars.middleware.view.model.repo.handler;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;

@RepositoryEventHandler(DiscoveryView.class)
public class DiscoveryViewEventHandler extends ViewEventHandler<DiscoveryView> {

    public static final String DISCOVERY_VIEWS_CHANNEL = "/queue/discovery-views";

    @Override
    protected String getChannel() {
        return DISCOVERY_VIEWS_CHANNEL;
    }

}
