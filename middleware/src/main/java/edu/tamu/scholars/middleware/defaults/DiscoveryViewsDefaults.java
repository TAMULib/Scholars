package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;

@Service
public class DiscoveryViewsDefaults extends AbstractDefaults<DiscoveryView, DiscoveryViewRepo> {

    public DiscoveryViewsDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/discoveryViews";
    }

    @Override
    public DiscoveryView read(InputStream is) throws IOException {
        DiscoveryView view = mapper.readValue(is, DiscoveryView.class);
        for (Map.Entry<String, String> entry : view.getTemplates().entrySet()) {
            String path = entry.getValue();
            Resource resource = resolver.getResource(String.format("classpath:%s", path));
            if (resource.exists() && resource.isFile()) {
                entry.setValue(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
            } else {
                logger.warn(String.format("Could not read %s. Either does not exists or is not a file.", path));
            }
        }
        return view;
    }

}
