package edu.tamu.scholars.middleware.theme.model.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import edu.tamu.scholars.middleware.model.repo.NamedRepo;
import edu.tamu.scholars.middleware.theme.model.Theme;

@RepositoryRestResource
public interface ThemeRepo extends NamedRepo<Theme> {

    @RestResource(path = "active", rel = "active")
    public Theme findByActiveTrue();

    @RestResource(exported = false)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Theme theme SET theme.active = false WHERE theme.active = true")
    public void deactivate();

}
