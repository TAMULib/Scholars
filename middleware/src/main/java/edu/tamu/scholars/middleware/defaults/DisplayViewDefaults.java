package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DisplayView;
import edu.tamu.scholars.middleware.view.model.repo.DisplayViewRepo;

@Service
public class DisplayViewDefaults extends AbstractDefaults<DisplayView, DisplayViewRepo> {

    @Override
    public String path() {
        return "classpath:defaults/displayViews";
    }

    @Override
    public DisplayView read(InputStream is) throws IOException {
        return mapper.readValue(is, DisplayView.class);
    }

}
