package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;

@Service
public class DiscoveryViewsDefaults extends AbstractDefaults<DiscoveryView, DiscoveryViewRepo> {

    public DiscoveryViewsDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/discoveryViews.yml";
    }

    @Override
    public List<DiscoveryView> read(InputStream is) throws IOException {
        // @formatter:off
        List<DiscoveryView> views = mapper.readValue(is, new TypeReference<List<DiscoveryView>>() {});
        // @formatter:on
        for (DiscoveryView view : views) {
            for (Map.Entry<String, String> entry : view.getTemplates().entrySet()) {
                String path = entry.getValue();
                Resource resource = resolver.getResource(String.format(CLASSPATH, path));
                if (resource.exists() && resource.isFile()) {
                    entry.setValue(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    throw new IOException(String.format(IO_EXCEPTION_MESSAGE, path));
                }
            }
        }
        return views;
    }

}
