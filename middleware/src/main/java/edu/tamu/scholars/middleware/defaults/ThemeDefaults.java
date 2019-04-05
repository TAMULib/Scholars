package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.theme.model.Theme;
import edu.tamu.scholars.middleware.theme.model.repo.ThemeRepo;

@Service
public class ThemeDefaults extends AbstractDefaults<Theme, ThemeRepo> {

    public ThemeDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/themes";
    }

    @Override
    public Theme read(InputStream is) throws IOException {
        return mapper.readValue(is, Theme.class);
    }

}
