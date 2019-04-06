package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.tamu.scholars.middleware.theme.model.Theme;
import edu.tamu.scholars.middleware.theme.model.repo.ThemeRepo;

@Service
public class ThemeDefaults extends AbstractDefaults<Theme, ThemeRepo> {

    public ThemeDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/themes.yml";
    }

    @Override
    public List<Theme> read(InputStream is) throws IOException {
        // @formatter:off
        return mapper.readValue(is, new TypeReference<List<Theme>>() {});
        // @formatter:on
    }

}
