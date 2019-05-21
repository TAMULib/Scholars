package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.tamu.scholars.middleware.view.model.DisplayTabSectionView;
import edu.tamu.scholars.middleware.view.model.DisplayTabView;
import edu.tamu.scholars.middleware.view.model.DisplayView;
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
        // @formatter:off
        List<DisplayView> views = mapper.readValue(is, new TypeReference<List<DisplayView>>() {});
        // @formatter:on
        for (DisplayView view : views) {
            if (view.getMainContentTemplate() != null && view.getMainContentTemplate().length() > 0) {
                try {
                    view.setMainContentTemplate(getTemplate(view.getMainContentTemplate()));
                } catch (IOException e) {
                    logger.warn(String.format(IO_EXCEPTION_MESSAGE, view.getMainContentTemplate()));
                }
            }
            if (view.getLeftScanTemplate() != null && view.getLeftScanTemplate().length() > 0) {
                try {
                    view.setLeftScanTemplate(getTemplate(view.getLeftScanTemplate()));
                } catch (IOException e) {
                    logger.warn(String.format(IO_EXCEPTION_MESSAGE, view.getLeftScanTemplate()));
                }
            }
            if (view.getRightScanTemplate() != null && view.getRightScanTemplate().length() > 0) {
                try {
                    view.setRightScanTemplate(getTemplate(view.getRightScanTemplate()));
                } catch (IOException e) {
                    logger.warn(String.format(IO_EXCEPTION_MESSAGE, view.getRightScanTemplate()));
                }
            }
            if (view.getAsideTemplate() != null && view.getAsideTemplate().length() > 0) {
                try {
                    view.setAsideTemplate(getTemplate(view.getAsideTemplate()));
                } catch (IOException e) {
                    logger.warn(String.format(IO_EXCEPTION_MESSAGE, view.getAsideTemplate()));
                }
            }
            if (view.getTabs() != null) {
                for (DisplayTabView tabView : view.getTabs()) {
                    if (tabView.getSections() != null) {
                        for (DisplayTabSectionView section : tabView.getSections()) {
                            section.setTemplate(getTemplate(section.getTemplate()));
                        }
                    }
                }
            }
            loadTemplateMap(view.getMetaTemplates());
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
