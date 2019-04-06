package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DisplayTabSectionView;
import edu.tamu.scholars.middleware.view.model.DisplayView;
import edu.tamu.scholars.middleware.view.model.DisplayTabView;
import edu.tamu.scholars.middleware.view.model.repo.DisplayViewRepo;

@Service
public class DisplayViewDefaults extends AbstractDefaults<DisplayView, DisplayViewRepo> {

    public DisplayViewDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/displayViews.yml";
    }

    @Override
    public List<DisplayView> read(InputStream is) throws IOException {
        List<DisplayView> views = mapper.readValue(is, ENTITY_TYPE_REF);
        for (DisplayView view : views) {
            view.setMainContentTemplate(getTemplate(view.getMainContentTemplate()));
            view.setLeftScanTemplate(getTemplate(view.getLeftScanTemplate()));
            view.setRightScanTemplate(getTemplate(view.getRightScanTemplate()));
            for (DisplayTabView tabView : view.getTabs()) {
                for (DisplayTabSectionView section : tabView.getSections()) {
                    section.setTemplate(getTemplate(section.getTemplate()));
                }
            }
        }
        return views;
    }

    private String getTemplate(String path) throws IOException {
        Resource resource = resolver.getResource(String.format(CLASSPATH, path));
        if (resource.exists() && resource.isFile()) {
            return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        }
        throw new IOException(String.format(IO_EXCEPTION_MESSAGE, path));
    }

}
