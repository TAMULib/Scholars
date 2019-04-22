package edu.tamu.scholars.middleware.view.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.tamu.scholars.middleware.view.model.DisplayView;

@RepositoryRestResource
public interface DisplayViewRepo extends ViewRepo<DisplayView> {

    public Optional<DisplayView> findByTypesIn(List<String> types);

}
