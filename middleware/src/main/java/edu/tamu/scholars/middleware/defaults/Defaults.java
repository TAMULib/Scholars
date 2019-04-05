package edu.tamu.scholars.middleware.defaults;

import java.io.IOException;
import java.io.InputStream;

import edu.tamu.scholars.middleware.theme.model.Named;
import edu.tamu.scholars.middleware.theme.model.repo.NamedRepo;

public interface Defaults<E extends Named, R extends NamedRepo<E>> {

    public String path();

    public E read(InputStream is) throws IOException;

    public E load(String name) throws IOException;

    public void load() throws IOException;

    public void process(E entity);

    public void update(E entity, E existingEntity);

}
