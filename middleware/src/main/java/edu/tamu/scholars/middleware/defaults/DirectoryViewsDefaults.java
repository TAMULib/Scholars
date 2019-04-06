package edu.tamu.scholars.middleware.defaults;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.repo.DirectoryViewRepo;

@Service
public class DirectoryViewsDefaults extends CollectionViewsDefaults<DirectoryView, DirectoryViewRepo> {

    public DirectoryViewsDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/directoryViews.yml";
    }

}
