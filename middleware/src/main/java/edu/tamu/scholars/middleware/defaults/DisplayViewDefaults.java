package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.view.model.DisplaySection;
import edu.tamu.scholars.middleware.view.model.DisplayView;
import edu.tamu.scholars.middleware.view.model.TabView;
import edu.tamu.scholars.middleware.view.model.repo.DisplayViewRepo;

@Service
public class DisplayViewDefaults extends AbstractDefaults<DisplayView, DisplayViewRepo> {

    public DisplayViewDefaults() {
        super();
    }

    @Override
    public String path() {
        return "classpath:defaults/displayViews";
    }

    @Override
    public DisplayView read(InputStream is) throws IOException {
        DisplayView view = mapper.readValue(is, DisplayView.class);

        Resource resource = resolver.getResource(String.format("classpath:%s", view.getMainContentTemplate()));
        if (resource.exists() && resource.isFile()) {
            view.setMainContentTemplate(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
        } else {
            logger.warn(String.format("Could not read %s. Either does not exists or is not a file.", view.getMainContentTemplate()));
        }

        resource = resolver.getResource(String.format("classpath:%s", view.getLeftScanTemplate()));
        if (resource.exists() && resource.isFile()) {
            view.setLeftScanTemplate(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
        } else {
            logger.warn(String.format("Could not read %s. Either does not exists or is not a file.", view.getLeftScanTemplate()));
        }

        resource = resolver.getResource(String.format("classpath:%s", view.getRightScanTemplate()));
        if (resource.exists() && resource.isFile()) {
            view.setRightScanTemplate(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
        } else {
            logger.warn(String.format("Could not read %s. Either does not exists or is not a file.", view.getRightScanTemplate()));
        }

        for (TabView tabView : view.getTabs()) {
            for (DisplaySection section : tabView.getSections()) {

                resource = resolver.getResource(String.format("classpath:%s", section.getTemplate()));
                if (resource.exists() && resource.isFile()) {
                    section.setTemplate(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    logger.warn(String.format("Could not read %s. Either does not exists or is not a file.", section.getTemplate()));
                }

            }
        }

        return view;
    }

}
