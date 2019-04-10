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

import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.repo.DirectoryViewRepo;

@Service
public class DirectoryViewsDefaults extends AbstractDefaults<DirectoryView, DirectoryViewRepo> {

    public DirectoryViewsDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/directoryViews.yml";
    }

    @Override
    public List<DirectoryView> read(InputStream is) throws IOException {
        // @formatter:off
        List<DirectoryView> views = mapper.readValue(is, new TypeReference<List<DirectoryView>>() {});
        // @formatter:on
        for (DirectoryView view : views) {
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
