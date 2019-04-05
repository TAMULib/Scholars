package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.repo.DirectoryViewRepo;

@Service
public class DirectoryViewsDefaults extends AbstractDefaults<DirectoryView, DirectoryViewRepo> {

    public DirectoryViewsDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/directoryViews";
    }

    @Override
    public DirectoryView read(InputStream is) throws IOException {
        DirectoryView view = mapper.readValue(is, DirectoryView.class);
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
