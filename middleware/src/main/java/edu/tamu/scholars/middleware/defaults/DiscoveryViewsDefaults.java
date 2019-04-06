package edu.tamu.scholars.middleware.defaults;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;

@Service
public class DiscoveryViewsDefaults extends CollectionViewsDefaults<DiscoveryView, DiscoveryViewRepo> {

    public DiscoveryViewsDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/discoveryViews.yml";
    }

}
