package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.repo.ViewRepo;

public abstract class CollectionViewsDefaults<V extends CollectionView, R extends ViewRepo<V>> extends AbstractDefaults<V, R> {

    public CollectionViewsDefaults() {
        super();
    }

    @Override
    public List<V> read(InputStream is) throws IOException {
        List<V> views = mapper.readValue(is, ENTITY_TYPE_REF);
        for (V view : views) {
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
