package edu.tamu.scholars.middleware.theme.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.theme.model.Theme;

@Repository
public interface ThemeRepo extends JpaRepository<Theme, Long> {

    @RestResource(path = "active", rel = "active")
    public Theme findByActiveTrue();

    @RestResource(exported = false)
    public Optional<Theme> findByName(String name);

    @RestResource(exported = false)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Theme theme SET theme.active = false WHERE theme.active = true")
    public void deactivate();

}
