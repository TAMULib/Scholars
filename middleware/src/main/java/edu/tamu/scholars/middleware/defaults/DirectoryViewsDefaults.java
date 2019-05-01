package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
            loadTemplateMap(view.getTemplates());
        }
        return views;
    }

}
