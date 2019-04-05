package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;

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
        return mapper.readValue(is, DirectoryView.class);
    }

}
