package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import edu.tamu.scholars.middleware.theme.model.Named;
import edu.tamu.scholars.middleware.theme.model.repo.NamedRepo;

public abstract class AbstractDefaults<E extends Named, R extends NamedRepo<E>> implements Defaults<E, R> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final ObjectMapper mapper;

    @Autowired
    protected ResourcePatternResolver resolver;

    @Autowired
    protected R repo;

    public AbstractDefaults() {
        mapper = new ObjectMapper(new YAMLFactory());
    }

    @Override
    public E load(String name) throws IOException {
        String path = String.format("%s/%s", path(), name);
        logger.info(String.format("Loading defaults from %s.", path));
        Resource resource = resolver.getResource(path);
        if (resource.exists() && resource.isFile()) {
            return read(resource.getInputStream());
        }
        throw new RuntimeException(String.format("Could not read %s. Either does not exists or is not a file.", path));
    }

    @Override
    public void load() throws IOException {
        Resource[] resources = resolver.getResources(String.format("%s/*.yml", path()));
        for (Resource resource : resources) {
            E entity = load(resource.getFilename());
            process(entity);
        }
    }

    @Override
    public void process(E entity) {
        Optional<E> existingEntity = repo.findByName(entity.getName());
        if (existingEntity.isPresent()) {
            update(entity, existingEntity.get());
        } else {
            repo.save(entity);
            logger.info(String.format("Created %s defaults.", entity.getName()));
        }
    }

    @Override
    public void update(E entity, E existingEntity) {
        logger.info(String.format("Updating %s defaults.", entity.getName()));
        BeanUtils.copyProperties(entity, existingEntity);
        repo.save(entity);
    }

}
