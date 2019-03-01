package edu.tamu.scholars.middleware.view;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;

public abstract class DiscoveryViewIntegrationTest extends UserIntegrationTest {

    @Autowired
    protected DiscoveryViewRepo directoryViewRepo;

    protected DiscoveryView getMockDiscoveryView() {
        DiscoveryView discoveryView = new DiscoveryView();

        return discoveryView;
    }

}
