package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;

@Service
public class DiscoveryViewsDefaults extends AbstractDefaults<DiscoveryView, DiscoveryViewRepo> {

    @Override
    public String path() {
        return "classpath:defaults/discoveryViews";
    }

    @Override
    public DiscoveryView read(InputStream is) throws IOException {
        return mapper.readValue(is, DiscoveryView.class);
    }

}
